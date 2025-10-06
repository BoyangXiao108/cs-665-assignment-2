package edu.bu.met.cs665.delivery;

import edu.bu.met.cs665.delivery.interfaces.IObserver;
import edu.bu.met.cs665.delivery.model.DeliveryRequest;
import edu.bu.met.cs665.delivery.model.Driver;
import edu.bu.met.cs665.delivery.model.DriverType;
import edu.bu.met.cs665.delivery.model.Shop;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DeliverySystemTest {

  private Driver d(String name) { return new Driver(name, DriverType.TAXI); }

  @Test
  @DisplayName("Broadcast to at least five drivers")
  void broadcastToAllDrivers() {
    Shop shop = new Shop("City Mart");
    Driver a = d("Alice"), b = d("Bob"), c = d("Carol"), d = d("Dan"), e = d("Eve");
    List<IObserver> drivers = List.of(a, b, c, d, e);
    drivers.forEach(shop::attach);

    DeliveryRequest req = shop.createDeliveryRequest("Store A", "Customer X", "Pizza", 1);

    for (IObserver o : drivers) {
      Driver drv = (Driver) o;
      assertEquals(1, drv.getInbox().size());
      assertEquals(req.getId(), drv.getInbox().get(0).getId());
    }
  }

  @Test
  void detachStopsNotification() {
    Shop shop = new Shop("Books & More");
    Driver a = d("Alice"), b = d("Bob"), c = d("Carol");
    shop.attach(a); shop.attach(b); shop.attach(c);
    shop.detach(b);

    DeliveryRequest req = shop.createDeliveryRequest("Store B", "Customer Y", "Books", 0);

    assertEquals(1, a.getInbox().size());
    assertEquals(req.getId(), a.getInbox().get(0).getId());
    assertEquals(0, b.getInbox().size()); // detached
    assertEquals(1, c.getInbox().size());
  }

  @Test
  void noDriversIsSafe() {
    Shop shop = new Shop("Coffee Corner");
    assertDoesNotThrow(() ->
        shop.createDeliveryRequest("Cafe", "Office 3F", "2x Latte", 0));
  }

  @Test
  void dynamicAttachAfterCreation() {
    Shop shop = new Shop("Green Grocery");
    Driver a = d("Alice"), b = d("Bob"), c = d("Carol");
    shop.attach(a); shop.attach(b);

    DeliveryRequest r1 = shop.createDeliveryRequest("Grocer", "Home A", "Veg Box", 1);
    assertEquals(1, a.getInbox().size());
    assertEquals(1, b.getInbox().size());
    assertEquals(0, c.getInbox().size());

    shop.attach(c);
    DeliveryRequest r2 = shop.createDeliveryRequest("Grocer", "Home B", "Fruit Box", 1);

    assertEquals(r1.getId(), a.getInbox().get(0).getId());
    assertEquals(r2.getId(), a.getInbox().get(1).getId());
    assertEquals(2, a.getInbox().size());
    assertEquals(2, b.getInbox().size());
    assertEquals(1, c.getInbox().size());
    assertEquals(r2.getId(), c.getInbox().get(0).getId());
  }
}
