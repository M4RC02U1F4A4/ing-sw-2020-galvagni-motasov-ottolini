package it.polimi.ingsw.PSP23.model;

/**
*   Enum for possible level status
*   BUILT -> the level is build and it is possible to buil upstairs
*   FREE -> the level is free and it is possible to build
*   CUPOLA -> the level is build and it is not possible to build upstaris
*   NOT_AVALIABLE -> other
*/
public enum Status {
    BUILT, FREE, CUPOLA, NOT_AVAILABLE;

}
