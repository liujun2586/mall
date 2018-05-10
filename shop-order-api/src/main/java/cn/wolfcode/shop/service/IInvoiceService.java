package cn.wolfcode.shop.service;

import cn.wolfcode.shop.domain.Invoice; /**
 * 发票相关接口
 */
public interface IInvoiceService {

    void save(Invoice invoice);
}
