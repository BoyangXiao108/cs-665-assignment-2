package edu.bu.met.cs665.delivery.interfaces;

import edu.bu.met.cs665.delivery.model.DeliveryRequest;

public interface ISubject {
  void attach(IObserver observer);
  void detach(IObserver observer);
  void notifyObservers(DeliveryRequest request);
}