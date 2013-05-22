/*!40014 SET FOREIGN_KEY_CHECKS=0 */;

-- Dumping database structure for naesta_cleanup
USE `naesta_cleanup`;

DROP TABLE IF EXISTS `schema_version` ;

SET @NUSER = 1 ;
SET @NCUSTOMER = 10 ;
SET @NSTATION  = 10;
SET @NDOCKPERSTATION = 3 ;
SET @NBIKEPERSTATION = 2 ;
SET @NDOCK = @NSTATION * @NDOCKPERSTATION ;
SET @NBIKE = @NSTATION * @NBIKEPERSTATION ;


/*!40000 ALTER TABLE `user` DISABLE KEYS */;
DELETE FROM `user`;
DELIMITER //
DROP PROCEDURE IF EXISTS populateUserProc//
CREATE PROCEDURE populateUserProc(N INT)
    BEGIN
        DECLARE x  INT;
        SET x = 1;
        WHILE x  <= N DO
            INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `usertype`, `firstname`, `middlename`, `lastname`, `email`, `address`, `phone`, `language`, `security_question_id`, `answer`, `status`, `uaid`, `passwordexpiretime`)
            VALUES (x, concat('user',x), concat('3a494a7773d1b99a977a97ed57b4dd96'), 1, 5, concat('firstname',x), concat('middlename',x), concat('lastname',x), 'soapui@publicbikesystem.com', ' 12345 test address', '5145145415', 'en', 1, 'answer', 'Active', concat('000',x), '2022-02-20 19:35:47');
            SET  x = x + 1;
        END WHILE;
    END//
CALL populateUserProc( @NUSER )   //
DELIMITER ;

/*!40000 ALTER TABLE `user` ENABLE KEYS */;


-- Dumping data for table naesta_cleanup.bike: ~169 rows (approximately)
/*!40000 ALTER TABLE `bike` DISABLE KEYS */;
DELETE FROM `bike`;
DELIMITER //
DROP PROCEDURE IF EXISTS populateBikeProc//
CREATE PROCEDURE populateBikeProc( NK INT , NperK INT)
       BEGIN
         DECLARE x  INT;
         DECLARE y  INT;
         DECLARE z  INT;
         DECLARE b  INT;

         SET x = 1; /* kios index */
         SET z = 1; /* dockingpoint index */
         SET b = 1; /* bike index */

         WHILE x  <= NK DO
          SET y = 1;
          WHILE y <= NperK DO
          
            IF ( y <= @NBIKEPERSTATION ) THEN
              INSERT INTO `bike` (`id`, `uaid`, `publictag`, `dpid`, `msnbc`, `last_dp_update`, `customer`, `technician`, `statusBKOperative`, `msnemb`, `obcn`, `registeredOn`, `activatedOn`, `statusBKDepl`, `statusBKUbi`, `bktype`, `isdeleted`, `is_missing`)
              VALUES ( b , concat('bike',b) , '', z , concat('msnbc-bike' , b) , NULL, NULL, NULL, 10, concat('msnemb-bike',b), concat('obcn-bike',b), '2012-09-24 17:03:16', NULL, 8, 0, NULL, 0, 0);                 
              SET b = b + 1 ;
            END IF;
          
            SET z = z + 1;
            SET y = y + 1;
          END WHILE;
                
          SET  x = x + 1;
        END WHILE;
       END//
CALL populateBikeProc( @NSTATION ,@NDOCKPERSTATION )   //    
DELIMITER ;

/*!40000 ALTER TABLE `bike` ENABLE KEYS */;


-- Dumping data for table naesta_cleanup.biketriangle
/*!40000 ALTER TABLE `biketriangle` DISABLE KEYS */;
DELETE FROM `biketriangle`;
DELIMITER //
DROP PROCEDURE IF EXISTS populateBikeTriangleProc//
CREATE PROCEDURE populateBikeTriangleProc( NK INT , NperK INT)
       BEGIN
               DECLARE x  INT;
               DECLARE y  INT;
               DECLARE z  INT;
               DECLARE b  INT;

               SET x = 1; /* kios index */
               SET z = 1; /* dockingpoint index */
               SET b = 1; /* bike id */
               WHILE x  <= NK DO
               
                SET y = 1;
                WHILE y <= NperK DO
              IF ( y <= @NBIKEPERSTATION ) THEN
              INSERT INTO `biketriangle` (`id`, `uaid`, `msnbc`, `msnemb`, `obcn`, `registeredOn`, `activatedOn`, `statusBKTOperative`, `bikeid`, `enabled`, `isdeleted`) VALUES
              (b, concat('triangle',b), concat('msnbc-tri',b) , concat('msnemb-tri',b) , concat('obcn-tri',b), '2012-09-24 17:02:15', NULL, 1, b , '1', 0);
              SET b = b + 1 ;
              END IF;
                    SET z = z + 1;
                  SET y = y + 1;
                END WHILE;
                
            SET  x = x + 1;
               END WHILE;
       END//
CALL populateBikeTriangleProc(@NSTATION,@NDOCKPERSTATION)   //    
DELIMITER ;


/*!40000 ALTER TABLE `biketriangle` ENABLE KEYS */;


-- Dumping data for table naesta_cleanup.cst
/*!40000 ALTER TABLE `cst` DISABLE KEYS */;
DELETE FROM `cst`;
DELIMITER //
DROP PROCEDURE IF EXISTS populateCSTProc//
CREATE PROCEDURE populateCSTProc(N INT)
       BEGIN
               DECLARE x  INT;
               SET x = 1;
               WHILE x  <= N DO
            INSERT INTO `cst` (`id`, `uaid`, `msnbc`, `msnemb`, `obcn`, `registeredOn`, `dpid`, `statusCSTOperative`, `statusCSTEnabled`, `isdeleted`)
            VALUES (x, concat('cst',x), concat('msnbc-cst',x), concat('msnemb-cst',x), concat('obcn-cst',x), '2012-10-10 10:25:16', x, 1, '1', 0);
              SET  x = x + 1;
               END WHILE;
       END//
CALL populateCSTProc( @NDOCK )   //    
DELIMITER ;

/*!40000 ALTER TABLE `cst` ENABLE KEYS */;

-- Dumping data for table naesta_cleanup.customer
-- make sure correct credit_card_id (3) is populated in the credit_card table before running this procedure
-- other credit-card-related fields will (probably) be removed in the nearest future 
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
DELETE FROM `customer`;
DELETE FROM `customergroup`;
DELETE FROM `payment_response_log`;
DELETE FROM `invoice`;
DELIMITER //
DROP PROCEDURE IF EXISTS populateCustomerProc//
CREATE PROCEDURE populateCustomerProc(N INT)
       BEGIN
               DECLARE x  INT;
               SET x = 1;
               WHILE x  <= N DO
                  INSERT INTO `customer` (`id`, `username`, `password`, `enabled`, `usertype`, `customerDetail_customerid`, `createdOn`, `security_question_id`, `answer`, `billing_customer_id`, `displayId`, `planId`, `uaid`, `credit_card_id`, `passwordExpireTime`)
                  VALUES ( x , CONCAT('customer' , x), '3a494a7773d1b99a977a97ed57b4dd96', 1, 'Subscriber', x , '2012-10-10 14:34:34', 3, 'patriots', x, concat('displayid' , x ), NULL, NULL, 3, '2022-02-20 19:35:47');
                  INSERT INTO `customergroup` (`groupid`, `customerid`)
                  VALUES ( 4, x);
                  INSERT INTO `invoice` (`id`, `create_time`, `customer_id`, `total_cost`, `total_tax1`, `total_tax2`, `invoice_total`, `payment_attempts`, `is_process_payment`, `deleted`, `status`)
                  VALUES (x, '2013-01-01 15:36:33', x, 95, 0, 0, 95, 1, 1, 0, 1);
                  INSERT INTO `payment_response_log` (`id`, `response_message`, `credit_card_id`, `invoice_id`, `create_time`, `success`)
                  VALUES (x, '1,1,1,This transaction has been approved.,3D37LV,Y,2183422858,,,95.00,CC,auth_capture,164,,,,,,,,,,,sfaubert@publicbikesystem.com,,,,,,,,,,,,,,159AF209A30608885BE28CE658451BC2,,2,,,,,,,,,,,XXXX5014,Visa,,,,,,,,,,,,,,,,', 3, x, '2013-01-01 15:36:33', 1);
                  SET  x = x + 1;
               END WHILE;
       END//
CALL populateCustomerProc( @NCUSTOMER )   //    
DELIMITER ;


/*!40000 ALTER TABLE `customer` ENABLE KEYS */;


-- Dumping data for table naesta_cleanup.customerdetail
/*!40000 ALTER TABLE `customerdetail` DISABLE KEYS */;
DELETE FROM `customerdetail`;
DELIMITER //
DROP PROCEDURE IF EXISTS populateCustomerDetailProc//
CREATE PROCEDURE populateCustomerDetailProc(N INT)
       BEGIN
               DECLARE x  INT;
               SET x = 1;
               WHILE x  <= N DO         
            INSERT INTO `customerdetail` (`id`, `address1`, `address2`, `address3`, `city`, `state`, `country`, `primarycutomerid`, `gender`, `language`, `birthdate`, `phone1`, `phone2`, `firstname`, `middlename`, `lastname`, `email`, `zipcode`, `relationwithprimarycustomer`, `customerid`, `phonenumbertype`, `alternatephonenumbertype`, `billing_address`, `billing_city`, `billing_zipcode`, `billing_state`, `billing_country`) VALUES
            (x, 'testaddress1', 'testaddress1', NULL, NULL, NULL, NULL, NULL, 0, NULL, '1979-10-10', NULL, NULL, CONCAT('CUSTOMER',x), NULL, 'TEST', NULL, '90210', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
            SET  x = x + 1;
               END WHILE;
       END//
CALL populateCustomerDetailProc( @NCUSTOMER )   //    
DELIMITER ;

/*!40000 ALTER TABLE `customerdetail` ENABLE KEYS */;



-- Dumping data for table naesta_cleanup.customerproduct: ~15 rows (approximately)
/*!40000 ALTER TABLE `customerproduct` DISABLE KEYS */;
DELETE FROM `customerproduct`;
DELIMITER //
DROP PROCEDURE IF EXISTS populateCPProc//
CREATE PROCEDURE populateCPProc(N INT)
       BEGIN
               DECLARE x  INT;
               SET x = 1;
               WHILE x  <= N DO
            INSERT INTO `customerproduct` (`id`, `amountRefunded`, `autoRenew`, `billingId`, `closureDate`, `endTime`, `holdAmount`, `reason`, `reminder`, `startTime`, `status`, `customerId`, `productId`, `suspendedBy`, `noOfBikes`, `purchaseDate`)
            VALUES (x, NULL, 'N', x, '2013-10-10 14:55:29', '2013-10-10 14:55:29', 0, NULL, NULL, '2012-10-10 14:55:29', 1, x, 178, NULL, 1, '2012-10-10 14:34:34');
                   SET  x = x + 1;
               END WHILE;
       END//
CALL populateCPProc( @NCUSTOMER )   //    
DELIMITER ;

/*!40000 ALTER TABLE `customerproduct` ENABLE KEYS */;



-- Dumping data for table naesta_cleanup.dockingpoint: ~89 rows (approximately)
/*!40000 ALTER TABLE `dockingpoint` DISABLE KEYS */;
DELETE FROM `dockingpoint`;
DELIMITER //
DROP PROCEDURE IF EXISTS populateDockingpointProc//
CREATE PROCEDURE populateDockingpointProc( NK INT , NperK INT)
       BEGIN
               DECLARE x  INT;
               DECLARE y  INT;
               DECLARE z  INT;

               SET x = 1; /* kios index */
               SET z = 1; /* dockingpoint index */
               WHILE x  <= NK DO
               
                SET y = 1;
                WHILE y <= NperK DO

              INSERT INTO `dockingpoint` (`id`, `stationid`, `uaid`, `previousstation`, `statusBDT`, `msnbc`, `msnemb`, `obcn`, `registeredOn`, `kskid`, `onKSKBus`, `atBusPosition`, `statusBDPConnection`, `lastactivity`, `istoservice`, `defectiveBKPressed`, `holdDefectiveBK`, `isDeleted`, `isLocked`, `isReboot`) VALUES
              (z, x, concat('dp',z), NULL, 1, concat("msnbc-dp",z), concat("msnemb-dp",z), '', '2012-09-24 16:02:47', x , NULL, NULL, 0, NULL, NULL, NULL, NULL, 0, 0, 0);
                
                  SET z = z + 1;
                  SET y = y + 1;
                END WHILE;
                
            SET  x = x + 1;
               END WHILE;
       END//
CALL populateDockingpointProc( @NSTATION , @NDOCKPERSTATION )   //    
DELIMITER ;

/*!40000 ALTER TABLE `dockingpoint` ENABLE KEYS */;



-- Dumping data for table naesta_cleanup.gpswireless: ~227 rows (approximately)
/*!40000 ALTER TABLE `gpswireless` DISABLE KEYS */;
DELETE FROM `gpswireless`;
DELIMITER //
DROP PROCEDURE IF EXISTS populateGPSWirelessProc//
CREATE PROCEDURE populateGPSWirelessProc( NK INT , NperK INT)
       BEGIN
               DECLARE x  INT;
               DECLARE y  INT;
               DECLARE z  INT;
               DECLARE b  INT;

               SET x = 1; /* kios index */
               SET z = 1; /* dockingpoint index */
               set b = 1; /* bike id */
               WHILE x  <= NK DO
               
                SET y = 1;
                WHILE y <= NperK DO
            IF ( y <= @NBIKEPERSTATION ) THEN
            INSERT INTO `gpswireless` (`id`, `uaid`, `msnbc`, `msnemb`, `obcn`, `macAddress`, `registeredOn`, `statusGPSWOperative`, `statusGPSWEnabled`, `bikeid`, `isdeleted`) VALUES
            (b, concat('g',b), concat('msnbc-gps',b), NULL, NULL, concat('mac-gps',b), NULL, 1, '1', b, 0);
                 SET b = b + 1 ;
            END IF;
                SET z = z + 1;
                  SET y = y + 1;
                END WHILE;
                
            SET  x = x + 1;
               END WHILE;
       END//
CALL populateGPSWirelessProc( @NSTATION , @NDOCKPERSTATION )   //    
DELIMITER ;

/*!40000 ALTER TABLE `gpswireless` ENABLE KEYS */;

-- Dumping data for table naesta_cleanup.keyinventory: ~552 rows (approximately)
/*!40000 ALTER TABLE `keyinventory` DISABLE KEYS */;
DELETE FROM  `key_shipment_batch`;
DELETE FROM  `keyinventory`;
DELIMITER //
DROP PROCEDURE IF EXISTS populateKeyInventoryProc//
CREATE PROCEDURE populateKeyInventoryProc(N INT)
  BEGIN
  DECLARE x  INT;
  INSERT INTO `key_shipment_batch` (`Id`, `Batch`, `CreatedBy`, `NoOfKeys`, `DateCreatedOn`, `Status` )
  VALUES (1, 1, 1, N, '2012-09-19 17:47:33', 1);
  SET x = 1;
    WHILE x  <= N DO
      INSERT INTO `keyinventory` (`id`, `createdBy`, `DateCreatedOn`, `Uaid`, `Msn_bc`, `Batch_id`, `date_key_assigned`, `date_key_activated`, `satus_key_assignment`, `satus_key_activation`, `satus_key_functional`, `satus_key_location`, `Technician_id`, `Msn_emb`, `Subscriber_id`, `date_key_encrypted`, `registeredOn`, `isDeleted`)
      VALUES (x, 1, '2012-09-24 17:11:26', concat('k',x), concat('msnbc-key',x), 1, '2012-09-24 19:01:19', '2012-09-13 12:25:31', 2, 1, 1, 1, NULL , concat('msnemb-key',x), x , '2012-09-24 17:11:26', '2012-09-24 17:11:26', 0);    
      SET  x = x + 1;
    END WHILE;
  END//
CALL populateKeyInventoryProc( @NCUSTOMER )   //    
DELIMITER ;

/*!40000 ALTER TABLE `keyinventory` ENABLE KEYS */;




/*!40000 ALTER TABLE `kiosk` DISABLE KEYS */;
DELETE FROM `kiosk`;
DELIMITER //
DROP PROCEDURE IF EXISTS populateKioskProc//
CREATE PROCEDURE populateKioskProc(N INT)
       BEGIN
               DECLARE x  INT;
               SET x = 1;
               WHILE x  <= N DO
          INSERT INTO `kiosk` (`id`, `stationid`, `msnbc`, `msnemb`, `obcn`, `registeredon`, `uaid`, `status_enabled`, `isDeleted`, `isReboot`, `kioskRemoteAccess`) VALUES
            ( x , x, CONCAT("msnbc-kiosk", x ), CONCAT("msnemb-kiosk",x) , NULL, '2012-09-24 17:18:36', concat('kiosk',x), 1, 0, 0, 0);
                   SET  x = x + 1;
               END WHILE;
       END//
CALL populateKioskProc( @NSTATION )   //    
DELIMITER ;
  
/*!40000 ALTER TABLE `kiosk` ENABLE KEYS */;


-- Dumping data for table naesta_cleanup.scu: ~3 rows (approximately)
/*!40000 ALTER TABLE `scu` DISABLE KEYS */;
DELETE FROM `scu`;
DELIMITER //
DROP PROCEDURE IF EXISTS populateSCUProc//
CREATE PROCEDURE populateSCUProc(N INT)
       BEGIN
               DECLARE x  INT;
               SET x = 1;
               WHILE x  <= N DO
            INSERT INTO `scu` (`id`, `uaid`, `msnbc`, `msnemb`, `obcn`, `macadress`, `registeredon`, `kiosk_id`, `status_operative`, `status_enable`) VALUES
            (x, concat('scu',x), concat('msnbc-scu',x), concat('msnemb-scu',x), concat('obcn-scu' ,x ) , concat('macadress-scu',x) , '2012-09-25 09:25:44', x , 1, '1');                   
            SET  x = x + 1;
               END WHILE;
       END//
CALL populateSCUProc( @NSTATION )   //    
DELIMITER ;

/*!40000 ALTER TABLE `scu` ENABLE KEYS */;


-- Dumping data for table naesta_cleanup.station

/*!40000 ALTER TABLE `station` DISABLE KEYS */;
DELETE FROM `station`;
DELIMITER //
DROP PROCEDURE IF EXISTS populateStationProc//
CREATE PROCEDURE populateStationProc(N INT)
       BEGIN
               DECLARE x  INT;
               SET x = 1;
               WHILE x  <= N DO
          
                INSERT INTO `station` (`id`, `name`, `location`, `latitude`, `longitude`, `status`, `dpcapacity`, `createdby`, `lastModifiedBy`, `uaid`, `isSponsored`, `sponsorId`, `landmark`, `stAddress1`, `stAddress2`, `city`, `postalCode`, `streetIntersection`, `dateCreated`, `dateLastModified`, `promotionalText`, `nearbyInterest`, `altitude`, `teststation`, `isdeleted`, `isReboot`, `isRebootSW`)
                VALUES ( x , concat( 'Station' , x ) , '', 40.755467, -73.986536, 1, 32, 'superuser', 'superuser', concat('station',x), NULL, NULL, '', 'Montreal', '', '', '', '', '2012-09-24 17:18:53', '2012-10-10 12:15:22', '', NULL, '', 0, 0, 0, 0);                   
                SET  x = x + 1;
               END WHILE;
       END//
CALL populateStationProc( @NSTATION )   //    
DELIMITER ;

-- Dumping data for table naesta_cleanup.nearbystation
/*!40000 ALTER TABLE `nearbystation` DISABLE KEYS */;
DELETE FROM `nearbystation`;
DELIMITER //
DROP PROCEDURE IF EXISTS populateNearbystationProc//
CREATE PROCEDURE populateNearbystationProc(N INT)
       BEGIN
               DECLARE x  INT;
               SET x = 1;
               WHILE x  <= N DO

                INSERT INTO `nearbystation` (`id`, `primarystationid`, `nearbystationid`, `distance`)
                VALUES ( x ,x, N+1-x, 100);
                SET  x = x + 1;
               END WHILE;
       END//
CALL populateNearbystationProc( @NSTATION )   //    
DELIMITER ;


-- ====================================================== DUMMY DATA ==============================================================


/*!40000 ALTER TABLE `credit_card` DISABLE KEYS */;
DELETE FROM `credit_card`;
INSERT INTO `credit_card` (`ID`, `CC_NUMBER`, `CC_NUMBER_PLAIN`, `CC_EXPIRY`, `NAME`, `DELETED`, `GATEWAY_KEY`, `CC_TYPE`)
  VALUES
  (3, '4729260134188442', '547', '2016-04-30', 'For You', 0, '10737214', NULL),
  (4, 'XXXX6354', '583', '2016-04-30', 'For You', 0, '10737214', NULL),
  (6, '4729260134188442', '547', '2016-04-30', 'For You', 0, '10764392', NULL),
  (7, '************8442', '547', '2016-04-30', 'For You', 0, '10764808', NULL);
/*!40000 ALTER TABLE `credit_card` ENABLE KEYS */;



-- Dumping data for table naesta_cleanup.giftdetail: ~29 rows (approximately)
DELETE FROM `giftdetail`;
/*!40000 ALTER TABLE `giftdetail` DISABLE KEYS */;
INSERT INTO `giftdetail` (`id`, `activationDate`, `amount`, `cancellationDate`, `creditAmount`, `redemptionNo`, `serialNo`, `status`, `gid`, `idx`)
VALUES (1, NULL, 95, NULL, 0.00, '10000001', '0000001', 5, 1, NULL);
/*!40000 ALTER TABLE `giftdetail` ENABLE KEYS */;

/*!40000 ALTER TABLE `giftmaster` DISABLE KEYS */;
DELETE FROM `giftmaster`;
INSERT INTO `giftmaster` (`id`, `allocationType`, `batchStatus`, `creationDate`, `enumerationStrategy`, `giftCertificateType`, `nbCertificates`, `activation`, `companyId`, `gid`, `subscriber`, `activationDate`, `amount`, `cancellationDate`, `expiryDate`, `creditRemaingAmount`, `planType`, `actualPlan`, `productPlanId`, `productId`, `uaid`)
VALUES (1, NULL, 5, '2013-01-01 01:01:01', '1', '2', 1, NULL, 1, 1, NULL, '2013-01-01 01:01:01', 95, NULL, '2115-01-01 01:01:01', 'N', NULL, NULL, NULL, 178, '0000001');
/*!40000 ALTER TABLE `giftmaster` ENABLE KEYS */;



DELETE FROM `TripInfo`;