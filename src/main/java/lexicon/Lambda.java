package lexicon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Lambda {
    static List<Product> products = new ArrayList<>(
        Arrays.asList(
        new Product("Bottles", 120, 500),
        new Product("Spoons", 12, 5),
        new Product("Paper", 5, 0)
            )
    );
    static List<Product> matching = new ArrayList<>();
    static Conditional findByStockZero = p -> p.getStock() == 0;
    static Conditional findByNameStartB = p -> p.getProductName().startsWith("B");
    static Conditional findByPriceBetweenHundredToHundredFifty = p -> p.getPrice() < 150 && p.getPrice() > 100;
    static Conditional findByStockBetweenZeroToTen = p -> p.getStock() < 10 && p.getStock() > 0;
    static Action increasePrice = p -> p.setPrice(p.getPrice()*1.5);


    public static void main(String[] args) {
        findAndDo(products, findByStockZero, p->matching.add(p));
        System.out.println(matching.toString());
        cleanList();
        findAndDo(products, findByNameStartB, p->matching.add(p));
        System.out.println(matching.toString());
        cleanList();
        findAndDo(products, findByPriceBetweenHundredToHundredFifty, p->matching.add(p));
        System.out.println(matching.toString());
        cleanList();
        findAndDoMultiAction(products, findByStockBetweenZeroToTen,increasePrice, p->matching.add(p));
        System.out.println(matching.toString());
    }
    public static void findAndDo(Collection<Product> products, Conditional conditional, Action action){
        for (Product product: products){
            if (conditional.test(product)){
                action.execute(product);
            }
        }
    }
    public static void findAndDoMultiAction(Collection<Product> products, Conditional conditional, Action action, Action action1){
        for (Product product: products){
            if (conditional.test(product)){
                action.execute(product);
                action1.execute(product);
            }
        }
    }
    public static void cleanList(){
        matching.removeAll(products);
    }
}
