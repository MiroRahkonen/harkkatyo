package com.example.harkkatyo;

import androidx.lifecycle.ViewModel;

public class DataViewModel extends ViewModel {

    protected int consumption_Clothing = 50, consumption_Electronics, consumption_Paper, consumption_Recreation;
    protected String consumption_URL = "https://ilmastodieetti.ymparisto.fi/ilmastodieetti/calculatorapi/v1/ConsumptionCalculator?" +
            "query.clothing="+consumption_Clothing+"&query.electronics="+consumption_Electronics+"&query.paper="+consumption_Paper+"&query.recreation="+consumption_Recreation;

    protected int housing_Area, housing_Residents;
    protected String housing_Type;
    protected String housing_URL = "https://ilmastodieetti.ymparisto.fi/ilmastodieetti/calculatorapi/v1/HousingCalculator/InfrastructureEstimate?" +
            "type="+housing_Type+"&area="+housing_Area+"&residents="+housing_Residents;

    protected int vehicle_Distance, vehicle_Passengers, vehicle_Year;
    protected String vehicle_FuelType,vehicle_Size;
    protected String vehicle_URL = "https://ilmastodieetti.ymparisto.fi/ilmastodieetti/calculatorapi/v1/TransportCalculator/CarEstimate?" +
            "query.buildYear="+vehicle_Year+"&query.driveDistance="+vehicle_Distance+"&query.size="+vehicle_Size+"&query.fuel="+vehicle_FuelType+"&query.passengerCount=100";
}
