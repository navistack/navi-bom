package org.navistack.framework.core;

class SeatsErrCategory extends AbstractErrCategory implements ErrCategory {
    public SeatsErrCategory() {
        putMessage(SeatsErrCode.INVALID_REQUEST.ordinal(), "e.g., bad XML");
        putMessage(SeatsErrCode.COULD_NOT_CONNECT.ordinal(), "Could not connect to server");
        putMessage(SeatsErrCode.INTERNAL_ERROR.ordinal(), "Service run short of resources");
        putMessage(SeatsErrCode.NO_RESPONSE.ordinal(), "Did not respond in time");
        putMessage(SeatsErrCode.NONEXISTENT_CLASS.ordinal(), "Requested class does not exist");
        putMessage(SeatsErrCode.NO_SEAT_AVAILABLE.ordinal(), "All seats booked");
    }

    @Override
    public String name() {
        return "seats";
    }
}
