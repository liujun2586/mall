package cn.wolfcode.shop.service.impl;

import cn.wolfcode.shop.domain.Invoice;
import cn.wolfcode.shop.mapper.InvoiceMapper;
import cn.wolfcode.shop.service.IInvoiceService;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class InvoiceServiceImpl implements IInvoiceService {
    @Autowired
    private InvoiceMapper invoiceMapper;

    @Override
    public void save(Invoice invoice) {
        invoiceMapper.insert(invoice);
    }
}
