package org.navistack.framework.core;

class FlightsErrCategory extends AbstractErrCategory implements ErrCategory {
    public FlightsErrCategory() {
        putMessage(FlightsErrCode.NONEXISTENT_LOCATIONS.ordinal(), "Nonexistent airport name in request");
        putMessage(FlightsErrCode.DATES_IN_THE_PAST.ordinal(), "Request for a date from the past");
        putMessage(FlightsErrCode.INVERTED_DATES.ordinal(), "Requested flight return date before departure date");
        putMessage(FlightsErrCode.NO_FLIGHTS_FOUND.ordinal(), "No flight combination found");
        putMessage(FlightsErrCode.PROTOCOL_VIOLATION.ordinal(), "Received malformed request");
        putMessage(FlightsErrCode.CONNECTION_ERROR.ordinal(), "Could not connect to server");
        putMessage(FlightsErrCode.RESOURCE_ERROR.ordinal(), "Insufficient resources");
        putMessage(FlightsErrCode.TIMEOUT.ordinal(), "Processing timed out");
    }

    @Override
    public String name() {
        return "flights";
    }
}
