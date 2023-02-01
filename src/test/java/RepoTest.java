import org.example.domain.Flower;
import org.example.domain.ProductforSale;
import org.example.domain.Ticket;
import org.example.domain.Tree;

import java.io.IOException;

public class RepoTest {
    private Repository repo = new Repository();

    public void Test(){
        repo.createFlowerShop("Pau");

        Flower flower = new Flower(1, "Rosa", 2, 2, "Verd");
        Tree tree = new Tree(2, "Pi", 3, 4, 3);
        try {
            repo.createFlower(flower);
            repo.createTree(tree);
            System.out.println(repo.findbyId(1, "Flower"));
            System.out.println(repo.findbyName("Rosa", "Flower"));
            System.out.println(repo.getAllProducts());
            Flower newflower = new Flower(1, "Lila", 3, 2, "Verd");
            repo.updateFlower(newflower);
            System.out.println(repo.getAllProducts());
            Ticket ticket = new Ticket();
            ProductforSale p1 = new ProductforSale(flower, 3);
            ProductforSale p2 = new ProductforSale(tree, 1);
            ticket.getProductforSales().add(p1);
            ticket.getProductforSales().add(p2);
            repo.createTicket(ticket);
            repo.createSell(ticket);
            System.out.println(repo.getAllSells().toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
