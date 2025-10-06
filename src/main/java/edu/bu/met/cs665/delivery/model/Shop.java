package edu.bu.met.cs665.delivery.model;

import edu.bu.met.cs665.delivery.interfaces.IObserver;
import edu.bu.met.cs665.delivery.interfaces.ISubject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/** Concrete Subject: Shop broadcasts new DeliveryRequest to all registered drivers. */
public class Shop implements ISubject {
  private final String name;
  private final List<IObserver> observers = new ArrayList<>();

  public Shop(String name) {
    this.name = Objects.requireNonNull(name, "name");
  }

  public String getName() { return name; }

  @Override
  public void attach(IObserver observer) {
    if (observer != null && !observers.contains(observer)) {
      observers.add(observer);
    }
  }

  @Override
  public void detach(IObserver observer) {
    observers.remove(observer);
  }

  @Override
  public void notifyObservers(DeliveryRequest request) {
    for (IObserver o : observers) {
      o.update(request);
    }
  }

  public List<IObserver> getObservers() { return Collections.unmodifiableList(observers); }

  /** Creates a request and immediately notifies observers. */
  public DeliveryRequest createDeliveryRequest(String pickup, String dropoff, String itemDesc, int priority) {
    DeliveryRequest req = new DeliveryRequest(pickup, dropoff, itemDesc, priority);
    notifyObservers(req);
    return req;
  }
}
