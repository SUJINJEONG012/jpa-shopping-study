package com.shopping.study.repository;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shopping.study.constant.ItemStatus;
import com.shopping.study.entity.Item;
import com.shopping.study.entity.QItem;



@SpringBootTest
@TestPropertySource(locations="classpath:application-test.properties")
class ItemRepositoryTest {
	
	@Autowired
    ItemRepository itemRepository;
	
	@PersistenceContext
	EntityManager em;
	
	
	@Test
    @DisplayName("상품 저장 테스트")
    public void createItemTest(){
        Item item = new Item();
        item.setItemNm("테스트 상품");
        item.setPrice(10000);
        item.setItemDetail("테스트 상품 상세 설명");
        item.setItemStatus(ItemStatus.SELL);
        item.setStockNumber(100);
        item.setRegTime(LocalDateTime.now());
        item.setUpdateTime(LocalDateTime.now());
        Item savedItem = itemRepository.save(item);
        System.out.println(savedItem.toString());
    }
	
	//데이터 생성을 위해서 10개의 상품을 저장
	public void createItemList() {
		for( int i=1; i<=10; i++) {
			Item item = new Item();
			item.setItemNm("테스트 상품" + i);
			item.setPrice(10000 + i);
			item.setItemDetail("테스트상품 상세"+ i);
			item.setItemStatus(ItemStatus.SELL);
			item.setStockNumber(100);
			item.setRegTime(LocalDateTime.now());
			item.setUpdateTime(LocalDateTime.now());
			Item savedItem = itemRepository.save(item);		
		}
	}
	
	
	@Test
	@DisplayName("상품명 조회 테스트")
	public void findByItemNmTest() {
		this.createItemList();
		List<Item> itemList = itemRepository.findByItemNm("테스트 상품1");
		for(Item item : itemList) {
			System.out.println(item.toString());
		}
	}
	
	@Test
	@DisplayName("상품명, 상품상세설명 or 테스트 ")	
	public void findByItemNmOrItemDetail() {
		this.createItemList();
		List<Item> itemList = itemRepository.findByItemNmOrItemDetail("테스트 상품1", "테스트 상품 상세 설명5");
		for(Item item : itemList) {
			System.out.println(item.toString());
		}
		
	}
	
	@Test
    @DisplayName("가격 LessThan 테스트")
    public void findByPriceLessThanTest(){
        this.createItemList();
        List<Item> itemList = itemRepository.findByPriceLessThan(10005);
        for(Item item : itemList){
            System.out.println(item.toString());
        }
    }

    @Test
    @DisplayName("가격 내림차순 조회 테스트")
    public void findByPriceLessThanOrderByPriceDesc(){
        this.createItemList();
        List<Item> itemList = itemRepository.findByPriceLessThanOrderByPriceDesc(10005);
        for(Item item : itemList){
            System.out.println(item.toString());
        }
    }
    
    
    @Test
    @DisplayName("@Query를 이용한 상품 조회 테스트")
    public void findByItemDetailTest() {
    	this.createItemList();
    	List<Item> itemList = itemRepository.findByItemDetail("테스트 상품 상세 설명");
    	for(Item item : itemList) {
    		System.out.println(item.toString());
    	}		
    }
    
    @Test
    @DisplayName("Querydsl 조회테스트 1")
    public void queryDlsTest() {
    	this.createItemList();
    	JPAQueryFactory queryFactory = new JPAQueryFactory(em);
    	QItem qItem = QItem.item;
    	JPAQuery<Item> query = queryFactory.selectFrom(qItem)
    			.where(qItem.itemStatus.eq(ItemStatus.SELL))
    			.where(qItem.itemDetail.like("%" + "테스트 상품 상세 설명" + "%"))
    			.orderBy(qItem.price.desc());
    	
    	List<Item> itemList = query.fetch();
    	
    	for(Item item : itemList) {
    		System.out.println(item.toString());
    	}
    }
    
   
	
	
	
	
}