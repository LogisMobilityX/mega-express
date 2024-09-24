package com.express.adapter.output.restClient.stock;

import com.express.application.port.output.stock.StockReader;

public class StockClient implements StockReader {

    @Override
    public int loadQuantity() {
        return 0;
    }
}
