package com.example.harkkatyo;

import androidx.lifecycle.ViewModel;

public class DataViewModel extends ViewModel {

    protected int consumption_Clothing=0, consumption_Electronics=0, consumption_Paper=0, consumption_Recreation=0;
    protected String consumption_URL = "https://ilmastodieetti.ymparisto.fi/ilmastodieetti/calculatorapi/v1/ConsumptionCalculator?" +
            "query.clothing="+consumption_Clothing+"&query.electronics="+consumption_Electronics+"&query.paper="+consumption_Paper+"&query.recreation="+consumption_Recreation;

    protected int housing_Area=0, housing_Residents=0;
    protected String housing_Type = "Flat";
    protected String housing_URL = "https://ilmastodieetti.ymparisto.fi/ilmastodieetti/calculatorapi/v1/HousingCalculator/InfrastructureEstimate?" +
            "type="+housing_Type+"&area="+housing_Area+"&residents="+housing_Residents;

    protected int vehicle_Distance=0, vehicle_Passengers=0, vehicle_Year=0;
    protected String vehicle_FuelType = "Gasoline",vehicle_Size = "Medium";
    protected String vehicle_URL = "https://ilmastodieetti.ymparisto.fi/ilmastodieetti/calculatorapi/v1/TransportCalculator/CarEstimate?" +
            "query.buildYear="+vehicle_Year+
            "&query.driveDistance="+vehicle_Distance+
            "&query.size="+vehicle_Size+
            "&query.fuel="+vehicle_FuelType+
            "&query.passengerCount="+vehicle_Passengers;
}
