package org.udea.proint1.ms.producto_backend.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import org.udea.proint1.ms.producto_backend.dto.Product;
import org.udea.proint1.ms.producto_backend.dao.ProductDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

@Controller
public class ProductRestController {
	static final Logger log = Logger.getLogger(ProductRestController.class);
	@Autowired
	ProductDAO productDAO;

	@RequestMapping("/find")
	@ResponseBody
	public Product getProduct(String code) {
		Product product = new Product();

		PropertyConfigurator.configure("log4j.properties");
		log.info("Log4j traza metodo find");
		try {

			product = productDAO.findByCode(code);
			if (!product.equals(null)) {

				log.info("Log4j Se obtiene el producto con el codigo ingesado  ");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("Log4j Error en Find " + e);

		}

		return product;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public String setProduct(Product product) {
		String bandera = "Done!";
		PropertyConfigurator.configure("log4j.properties");
		log.info("Log4j traza metodo save");
		try {
			productDAO.save(product);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			bandera = "";
			bandera = "Fail";
			log.error("Log4j Error en Save " + e);

		}

		return bandera;

	}
}
