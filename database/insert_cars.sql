USE epam_db;

INSERT INTO cars (brand, model, gearbox, manufactured_year, engine_type, price_per_day, class, image_path)
    VALUES ('Chevrolet', 'Camaro', 'automatic', '2009', 'diesel', 55.9, 'muscle', 'images/camaro.jpg'),
           ('Toyota', 'Tundra',  'automatic', '2010', 'diesel', 49.9, 'SUV', 'images/tundra.jpg'),
           ('Citroen', 'C5',  'manual', '2002', 'petrol', 19.9, 'sedan', 'images/c5.jpg'),
           ('Skoda', 'Octavia',  'manual', '2007', 'diesel', 23.9,  'hatchback', 'images/octavia_hb.jpg'),
           ('Volkswagen', 'Polo',  'automatic', '2012', 'diesel', 32.5,  'sedan', 'images/polo_s.jpg'),
           ('Ford', 'Escort',  'automatic', '2007', 'diesel', 15.1, 'wagon', 'images/escort_w.jpg'),
           ('Hummer', 'H3',  'automatic', '2005', 'diesel', 45.5, 'SUV', 'images/h3.jpg'),
           ('Dodge', 'RAM', 'automatic', '2009', 'diesel', 49.9, 'pickup', 'images/ram.jpg'),
           ('Alfa Romeo', '156', 'manual', '2006', 'diesel', 29.9,'sedan', 'images/156_s.jpg'),
           ('Mazda', 'RX8', 'manual', '2010', 'diesel', 69.9, 'sport', 'images/rx8.jpg'),
           ('Lexus', 'IS300',  'manual', '2005', 'petrol', 30,  'wagon', 'images/is300_w.jpg'),
           ('Lada', 'Vesta', 'manual', '2012', 'petrol', 20,  'sedan', 'images/vesta_s.jpg'),
           ('Audi', '80', 'manual', '1995', 'petrol', 19.9,  'sedan', 'images/80_s.jpg');