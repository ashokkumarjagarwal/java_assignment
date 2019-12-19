CREATE TABLE `aerobay`.`user` (
  `id` VARCHAR(45) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC));

CREATE TABLE `aerobay`.`role` (
  `id` VARCHAR(45) NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`));


CREATE TABLE `aerobay`.`user_role` (
  `id` VARCHAR(45) NOT NULL,
  `user_id` VARCHAR(45) NOT NULL,
  `role_id` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `index2` (`user_id` ASC, `role_id` ASC),
  INDEX `fk_user_role_2_idx` (`role_id` ASC),
  CONSTRAINT `fk_user_role_1`
    FOREIGN KEY (`user_id`)
    REFERENCES `aerobay`.`user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_user_role_2`
    FOREIGN KEY (`role_id`)
    REFERENCES `aerobay`.`role` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

INSERT INTO `aerobay`.`role` (`id`,`name`) VALUES ('2301b2aa51c34007a32fb72ad8b0c9a1','SA');
INSERT INTO `aerobay`.`role` (`id`,`name`) VALUES ('7b585bca3d2b4086a66b282534ddd851','ADMIN');
INSERT INTO `aerobay`.`role` (`id`,`name`) VALUES ('63b9bb9e25c349d583bfb22430d899ac','USER');

