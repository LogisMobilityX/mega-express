package com.express.adapter.output.persistence.jpa.stock;


import com.express.application.port.output.stock.StockReader;

public class StockJpaRepository implements StockReader {

    @Override
    public int loadQuantity() {
        return 1;
    }
}
