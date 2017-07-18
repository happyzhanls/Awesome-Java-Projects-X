// TrainCar.java - class for data structure assignment
// Author: Chris Wilcox
// Date:   2/17/2017
// Class:  CS165
// Email:  wilcox@cs.colostate,edu

public class TrainCar {

    // Types of cars
    public enum eType {
        ENGINE,
        TANKER,
        CABOOSE,
        PASSENGER,
        DINING,
        VIEWING,
        FREIGHT
    }
    
    // Train attributes
    public eType carType;    // type of car
    public int identifier;   // car identifier
    public String ownedBy;   // car owner
    // "Santa Fe"
    // "Burlington Northern"
    // "Union Pacific"
    // "Canadian Pacific"
    // "Amtrak"

    // Constructor override
    public TrainCar(eType type, int id, String owner) {
        this.carType = type;
        this.identifier = id;
        this.ownedBy = owner;
    }
    
    // toString override
    public String toString() {
        return identifier + 
                " (" + identifier + ", " + carType + ", " + ownedBy + ")";
    }

    // equals override
    public boolean equals(Object o) {
        if (o instanceof TrainCar) {
            TrainCar t = (TrainCar)o;
            if ((this.carType    == t.carType)    &&
                (this.identifier == t.identifier) &&
                (this.ownedBy    == t.ownedBy))
                    return true;
        }
        return false;
    }
}

