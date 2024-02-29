import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ShopRepoTest {

    Product product1 = new Product(1, "хлеб", 40);
    Product product2 = new Product(2, "молоко", 100);
    Product product3 = new Product(3, "чай", 300);

    @Test
    public void shopRepositoryRemoveByIdTest() {
        ShopRepository shopRepository = new ShopRepository();

        shopRepository.add(product1);
        shopRepository.add(product2);
        shopRepository.add(product3);

        shopRepository.removeById(3);

        Product[] expected = {product1, product2};
        Product[] actual = shopRepository.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shopRepositoryExceptionTest() {
        ShopRepository shopRepository = new ShopRepository();

        shopRepository.add(product1);
        shopRepository.add(product2);
        shopRepository.add(product3);

        Assertions.assertThrows(NotFoundException.class, () -> {
            shopRepository.removeById(10);
        });
    }

    @Test
    public void shopRepositoryAddByIdTest() {
        ShopRepository shopRepository = new ShopRepository();

        shopRepository.add(product1);
        shopRepository.add(product3);

        shopRepository.add(product2);

        Product[] expected = {product1, product3, product2};
        Product[] actual = shopRepository.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shopRepositoryAllExistExceptionTest() {
        ShopRepository shopRepository = new ShopRepository();

        shopRepository.add(product1);
        shopRepository.add(product2);
        shopRepository.add(product3);
        try {
            shopRepository.add(product3);
        } catch (AlreadyExistsException msg) {
            System.out.println("Элемент " + product3.getId() + " уже существует");
        }
        Assertions.assertThrows(AlreadyExistsException.class, () -> {
            shopRepository.add(product3);
        });
    }
}