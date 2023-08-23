package org.navistack.framework.core;

enum FlightsErrCode {
    NONEXISTENT_LOCATIONS,
    DATES_IN_THE_PAST,
    INVERTED_DATES,
    NO_FLIGHTS_FOUND,
    PROTOCOL_VIOLATION,
    CONNECTION_ERROR,
    RESOURCE_ERROR,
    TIMEOUT,
}
