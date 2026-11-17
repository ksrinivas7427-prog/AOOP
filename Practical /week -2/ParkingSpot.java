class ParkingSpot {
 private int spotId;
ParkingSpot(int spotId) {
if (spotId <= 0) {
 throw new IllegalArgumentException(
 "Spot ID must be positive"
 );
 }
 this.spotId = spotId;
 }
void displayDetails() {
 System.out.println("Spot ID: " + spotId);
}
}
