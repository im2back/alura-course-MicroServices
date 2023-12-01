CREATE TABLE tb_order_item (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  quantity int(11) NOT NULL,
  column_description varchar(255) DEFAULT NULL,
  order_id bigint(20) NOT NULL,
  PRIMARY KEY (id),
   FOREIGN KEY (order_id) REFERENCES `tb_order` (id)
)