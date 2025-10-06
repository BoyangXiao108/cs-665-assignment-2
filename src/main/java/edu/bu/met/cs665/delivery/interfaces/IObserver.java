package edu.bu.met.cs665.delivery.interfaces;

import edu.bu.met.cs665.delivery.model.DeliveryRequest;

public interface IObserver {
  void update(DeliveryRequest request);
}
