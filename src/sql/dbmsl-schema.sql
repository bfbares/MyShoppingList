CREATE TABLE `categories` (
  `id_category` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(128) NOT NULL,
  PRIMARY KEY (`id_category`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC));

CREATE TABLE `articles` (
  `id_article` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(128) NOT NULL,
  PRIMARY KEY (`id_article`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC));

CREATE TABLE `shops` (
  `id_shop` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(128) NOT NULL,
  PRIMARY KEY (`id_shop`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC));

CREATE TABLE `articles_categories` (
  `id_article` BIGINT UNSIGNED NOT NULL,
  `id_category` BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY (`id_article`, `id_category`),
  INDEX `articles_categories_categories_fk_idx` (`id_category` ASC),
  CONSTRAINT `articles_categories_articles_fk`
  FOREIGN KEY (`id_article`)
  REFERENCES `articles` (`id_article`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `articles_categories_categories_fk`
  FOREIGN KEY (`id_category`)
  REFERENCES `categories` (`id_category`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE `carts` (
  `id_cart` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(128) NULL,
  `created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `bought` TIMESTAMP NULL,
  `id_shop` BIGINT UNSIGNED NULL,
  PRIMARY KEY (`id_cart`),
  INDEX `carts_shops_fk_idx` (`id_shop` ASC),
  CONSTRAINT `carts_shops_fk`
  FOREIGN KEY (`id_shop`)
  REFERENCES `shops` (`id_shop`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE `articles_carts` (
  `id_article_cart` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `id_article` BIGINT UNSIGNED NOT NULL,
  `id_cart` BIGINT UNSIGNED NOT NULL,
  `quantity` SMALLINT NOT NULL DEFAULT 1,
  PRIMARY KEY (`id_article_cart`),
  UNIQUE INDEX `article_cart_unique` (`id_article` ASC, `id_cart` ASC),
  INDEX `articles_carts_carts_fk_idx` (`id_cart` ASC),
  CONSTRAINT `articles_carts_articles_fk`
  FOREIGN KEY (`id_article`)
  REFERENCES `articles` (`id_article`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `articles_carts_carts_fk`
  FOREIGN KEY (`id_cart`)
  REFERENCES `carts` (`id_cart`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE `articles_shops` (
  `id_article_shop` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `id_article` BIGINT UNSIGNED NOT NULL,
  `id_shop` BIGINT UNSIGNED NOT NULL,
  `price` DECIMAL(10,4) UNSIGNED NOT NULL,
  `price_date` DATE NOT NULL,
  PRIMARY KEY (`id_article_shop`),
  INDEX `articles_shops_articles_fk_idx` (`id_article` ASC),
  INDEX `articles_shops_shops_fk_idx` (`id_shop` ASC),
  CONSTRAINT `articles_shops_articles_fk`
  FOREIGN KEY (`id_article`)
  REFERENCES `articles` (`id_article`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `articles_shops_shops_fk`
  FOREIGN KEY (`id_shop`)
  REFERENCES `shops` (`id_shop`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
