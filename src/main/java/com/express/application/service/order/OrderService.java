package com.express.application.service.order;

import com.express.application.port.input.order.OrderUseCase;
import com.express.application.port.output.order.OrderProcessor;
import com.express.application.port.output.stock.StockReader;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderService implements OrderUseCase{

    private final OrderProcessor orderProcessor;
    private final StockReader stockReader;

    @Override
    public void createOrder() {

        int quantity = stockReader.loadQuantity();
        if (quantity < 0) {
            throw new RuntimeException();
        }

        orderProcessor.order();
    }
}
