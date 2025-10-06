package edu.bu.met.cs665.delivery.model;

import edu.bu.met.cs665.delivery.interfaces.IObserver;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/** Concrete Observer: driver who receives delivery notifications. */
public class Driver implements IObserver {
  private final String name;
  private final DriverType type;
  private boolean available = true;
  private final List<DeliveryRequest> inbox = new ArrayList<>();

  public Driver(String name, DriverType type) {
    this.name = Objects.requireNonNull(name, "name");
    this.type = Objects.requireNonNull(type, "type");
  }

  public String getName() { return name; }
  public DriverType getType() { return type; }
  public boolean isAvailable() { return available; }
  public void setAvailable(boolean available) { this.available = available; }

  /** Read-only view for tests. */
  public List<DeliveryRequest> getInbox() { return Collections.unmodifiableList(inbox); }

  @Override
  public void update(DeliveryRequest request) {
    inbox.add(request);
  }

  @Override
  public String toString() {
    return "Driver{" + "name='" + name + '\'' + ", type=" + type + ", available=" + available + '}';
  }
}
