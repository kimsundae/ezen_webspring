package example.task.teamTask2;

import java.util.List;

public class Main {
    public static void main(String[] args) {


        Category category = Category.builder().cno(1).cname("스마트폰").build();

        Product product1 = Product.builder().pno(1).pname("아이폰15").build();
        Product product2 = Product.builder().pno(2).pname("갤럭시14").build();

        product1.setCategory(category); product2.setCategory(category);

        category.getProductList().add(product1);
        category.getProductList().add(product2);

        Order order1 = Order.builder().ono(1).oprice(1500000).product(product1).build();
        Order order2 = Order.builder().ono(2).oprice(1200000).product(product2).build();

        product1.setOrder(order1);
        product2.setOrder(order2);

        System.out.println("===================카테고리==================");
        System.out.println("카테고리 번호 : " + category.getCno() + " 카테고리 이름 : " + category.getCname() );
        for( int i = 0; i < category.getProductList().size(); i++ ){
            Product product = category.getProductList().get(i);
            Order order = product.getOrder();
            System.out.println("===================제품==================");
            System.out.println( "제품 번호 : " + product.getPno() + " 제품 이름 : "
                    + product.getPname() + " \n제품의 주문 번호 : " + order.getOno() + " 제품의 가격 : " + order.getOprice()  );

        }
        System.out.println("\n\norder1.getProduct().getCategory().getCname() << 코드를 통해 카테고리를 호출한 결과 = "
                + order1.getProduct().getCategory().getCname() );

    }
}
