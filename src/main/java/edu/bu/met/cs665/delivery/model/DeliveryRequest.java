package edu.bu.met.cs665.delivery.model;

import java.util.Objects;
import java.util.UUID;

/** Immutable value object for a delivery request. */
public final class DeliveryRequest {
  private final String id;
  private final String pickup;
  private final String dropoff;
  private final String itemDescription;
  private final int priority;

  public DeliveryRequest(String pickup, String dropoff, String itemDescription, int priority) {
    this(UUID.randomUUID().toString(), pickup, dropoff, itemDescription, priority);
  }

  public DeliveryRequest(String id, String pickup, String dropoff, String itemDescription, int priority) {
    this.id = Objects.requireNonNull(id, "id");
    this.pickup = Objects.requireNonNull(pickup, "pickup");
    this.dropoff = Objects.requireNonNull(dropoff, "dropoff");
    this.itemDescription = Objects.requireNonNull(itemDescription, "itemDescription");
    this.priority = priority;
  }

  public String getId() { return id; }
  public String getPickup() { return pickup; }
  public String getDropoff() { return dropoff; }
  public String getItemDescription() { return itemDescription; }
  public int getPriority() { return priority; }

  @Override
  public String toString() {
    return "DeliveryRequest{" +
        "id='" + id + '\'' +
        ", pickup='" + pickup + '\'' +
        ", dropoff='" + dropoff + '\'' +
        ", itemDescription='" + itemDescription + '\'' +
        ", priority=" + priority +
        '}';
  }
}
