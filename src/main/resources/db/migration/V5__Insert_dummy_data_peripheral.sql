INSERT INTO gateways.peripheral (created_date, status, vendor, gateway_id)
                        VALUES('2021-07-27 12:34:56.401000000', 0, 'IBM', (SELECT gateway_id from gateway where gateway_id=1));
INSERT INTO gateways.peripheral (created_date, status, vendor, gateway_id)
                        VALUES('2021-06-26 12:34:56.401000000', 1, 'CISCO', (SELECT gateway_id from gateway where gateway_id=1));
INSERT INTO gateways.peripheral (created_date, status, vendor, gateway_id)
                        VALUES('2021-05-25 12:34:56.401000000', 1, 'LINKQ', (SELECT gateway_id from gateway where gateway_id=1));
INSERT INTO gateways.peripheral (created_date, status, vendor, gateway_id)
                        VALUES('2021-04-24 12:34:56.401000000', 0, 'IBM', (SELECT gateway_id from gateway where gateway_id=2));
INSERT INTO gateways.peripheral (created_date, status, vendor, gateway_id)
                        VALUES('2021-03-23 12:34:56.401000000', 1, 'ORANGE', (SELECT gateway_id from gateway where gateway_id=3));
INSERT INTO gateways.peripheral (created_date, status, vendor, gateway_id)
                        VALUES('2021-02-22 12:34:56.401000000', 1, 'VODAFONE', (SELECT gateway_id from gateway where gateway_id=4));
INSERT INTO gateways.peripheral (created_date, status, vendor, gateway_id)
                        VALUES('2021-01-21 12:34:56.401000000', 1, 'TESLA', (SELECT gateway_id from gateway where gateway_id=5));
INSERT INTO gateways.peripheral (created_date, status, vendor, gateway_id)
                        VALUES('2020-12-20 12:34:56.401000000', 1, 'NGIX', (SELECT gateway_id from gateway where gateway_id=1));
