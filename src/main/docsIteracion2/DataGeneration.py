import random
import string
import sys
import os

names = [
    "Alice", "Bob", "Charlie", "David", "Eva", "Frank", "Grace", "Hannah", "Isaac", "Julia",
    "Kevin", "Laura", "Michael", "Nora", "Oliver", "Penny", "Quincy", "Rachel", "Samuel", "Tina",
    "Ulysses", "Victoria", "William", "Xander", "Yasmine", "Zachary",
    "Ava", "Benjamin", "Catherine", "Daniel", "Emily", "Felix", "Gabrielle", "Henry", "Isabel", "Jacob",
    "Katherine", "Liam", "Mia", "Nathan", "Olivia", "Peter", "Quinn", "Rebecca", "Samuel", "Tara",
    "Uma", "Vincent", "Wendy", "Xavier", "Yvonne", "Zane",
    "Amelia", "Brandon", "Chloe", "Dylan", "Ella", "Frederick", "Grace", "Harrison", "Ivy", "James",
    "Kylie", "Lucas", "Madison", "Natalie", "Oscar", "Penelope", "Quinn", "Riley", "Sophia", "Tristan",
    "Ursula", "Violet", "William", "Xena", "Yara", "Zoe"
]

lastnames = [
    "Anderson", "Baker", "Clark", "Davis", "Evans", "Fisher", "Garcia", "Harris", "Ingram", "Johnson",
    "Kaplan", "Lopez", "Martinez", "Nelson", "Owens", "Perez", "Quinn", "Ramirez", "Smith", "Taylor",
    "Ullman", "Valdez", "Williams", "Xiong", "Young", "Zhang",
    "Adams", "Brown", "Carter", "Diaz", "Edwards", "Flores", "Gomez", "Hernandez", "Irwin", "Jackson",
    "Kennedy", "Lee", "Mitchell", "Nguyen", "Ortiz", "Patel", "Rodriguez", "Scott", "Thomas", "Upton",
    "Vargas", "White", "Xu", "Yates", "Zimmerman",
    "Bell", "Cooper", "Dixon", "Ellis", "Ferguson", "Gordon", "Harrison", "Isaacs", "Jones", "Keller",
    "Lewis", "Miller", "Nixon", "OConnor", "Parker", "Quinn", "Roberts", "Sullivan", "Turner", "Upton",
    "Vasquez", "Walker", "Xu", "Yoder", "Zimmerman",
    "Allen", "Butler", "Cruz", "Davies", "Elliott", "Ford", "Gonzalez", "Hall", "Iverson", "Jenkins",
    "Khan", "Lopez", "Morgan", "Nguyen", "Owens", "Perez", "Quinn", "Reyes", "Stewart", "Tran",
    "Ullman", "Vargas", "Watson", "Xu", "Young", "Zimmerman"
]

services = {
    "Wifi Gratis": ("Conexión a Internet de alta velocidad en todas las áreas del hotel para que puedas estar siempre conectado." , "Internet"),
    "Desayuno Buffet": ("Disfruta de un desayuno variado con opciones calientes y frías, perfecto para comenzar tu día." , "Restaurante"),
    "Estacionamiento": ("Amplio estacionamiento seguro para los huéspedes que llegan en automóvil." , "Estacionamiento"),
    "Piscina al Aire Libre": ("Una piscina espaciosa con áreas de descanso para relajarte bajo el sol." , "Piscina"),
    "Gimnasio": ("Equipo de ejercicio moderno y un gimnasio bien equipado para mantenerse en forma durante tu estadía." , "Gimnasio"),
    "Servicio de Habitación 24/7": ("Solicita comida, bebidas y servicios a tu habitación en cualquier momento del día o la noche." , "Servicio de Habitación"),
    "Servicio de Lavandería": ("Lavado y planchado de ropa disponible para los huéspedes." , "Lavandería"),
    "Spa y Masajes": ("Relájate y rejuvenece con tratamientos de spa y masajes en el lugar." , "Spa"),
    "Restaurante en el Hotel": ("Saborea deliciosas comidas en el restaurante del hotel con opciones gastronómicas variadas." , "Restaurante"),
    "Bar y Lounge": ("Un lugar ideal para disfrutar de bebidas y socializar con otros huéspedes." , "Bar"),
    "Recepción 24/7": ("Atención al cliente disponible en todo momento para ayudarte con tus necesidades." , "Recepción"),
    "Servicio de Conserjería": ("Consejos y asistencia personalizada para planificar actividades y excursiones locales." , "Conserjería"),
    "Transporte al Aeropuerto": ("Traslado desde y hacia el aeropuerto para tu comodidad." , "Transporte"),
    "Salas de Reuniones": ("Espacios para conferencias y reuniones de negocios con servicios audiovisuales." , "Salón"),
    "Centro de Negocios": ("Acceso a computadoras, impresoras y servicios de oficina para viajeros de negocios." , "Salón"),
    "Guardería Infantil": ("Cuidado de niños y actividades para los más pequeños." , "Guardería"),
    "Admisión de Mascotas": ("Alojamiento que permite traer a tus mascotas contigo durante tu estadía." , "Mascotas"),
    "Servicio de Cambio de Divisas": ("Cambio de moneda extranjera disponible en el hotel." , "Divisas"),
    "Alquiler de Bicicletas": ("Explora el área local en bicicleta con el alquiler de bicicletas del hotel." , "Deporte"),
    "Concierge de Playa": ("Servicio de conserjería en la playa para hacer que tu día en la costa sea perfecto." , "Conserjería"),
    "Acceso a la Playa": ("Acceso directo a la playa para disfrutar de días de sol y mar." , "Playa"),
    "Programa de Entretenimiento Nocturno": ("Actividades y entretenimiento en vivo por la noche para disfrutar de tu estancia." , "Entretenimiento"),
    "Alquiler de Equipos de Deportes Acuáticos": ("Opciones como kayak, paddleboard y esnórquel disponibles para los huéspedes." , "Deporte"),
    "Servicio de Bodas y Eventos": ("Planificación y coordinación de bodas y eventos especiales en el hotel." , "Salón"),
    "Servicio de Limpieza en Seco": ("Limpieza en seco y planchado de ropa disponible para eventos formales." , "Lavandería"),
    "Programa de Recompensas para Huéspedes Frecuentes": ("Ofertas y beneficios especiales para clientes habituales." , "Recompensas"),
    "Alquiler de Coches": ("Alquiler de automóviles directamente desde el hotel para explorar la zona." , "Transporte"),
    "Programas de Ejercicio y Yoga": ("Clases y actividades de fitness para mantenerse activo durante tu estancia." , "Deporte"),
    "Servicio de Alquiler de DVD": ("Peliculas y entretenimiento en tu habitación para las noches de descanso." , "Entretenimiento"),
    "Espacios para No Fumadores": ("Espacios libre de humo para huéspedes no fumadores." , "Otros"),
    "Espacios con Vistas al Mar": ("Disfruta de impresionantes vistas al océano." , "Otros"),
    "Espacios con Jacuzzi Privado": ("Espacios con jacuzzi para relajarte en privado." , "Otros"),
    "Espacios Familiares": ("Espacio adicional y comodidades para familias que viajan juntas." , "Otros"),
    "Lounrge Privada": ("Espacio de lujo privado con amplios espacios y comodidades exclusivas." , "Otros"),
    "Espacios Adaptadados para Personas con Discapacidad": ("Instalaciones y servicios accesibles para huéspedes con necesidades especiales." , "Otros"),
    "Programa de Excursiones Locales": ("Organización de salidas y tours para explorar los alrededores." , "Excursiones"),
    "Alquiler de Equipo de Esquí": ("Equipamiento de esquí para huéspedes que visitan destinos de montaña." , "Deporte"),
    "Servicio de Reservas de Restaurantes": ("Ayuda con las reservas en los restaurantes locales más populares." , "Restaurante"),
    "Área de Juegos para Niños": ("Espacio dedicado para que los niños se diviertan y jueguen." , "Entretenimiento"),
    "Servicio de Información Turística": ("Consejos y guías turísticas para explorar la zona." , "Excursiones"),
    "Servicio de Recepción de Paquetes": ("Recepción y almacenamiento de paquetes y entregas." , "Otros"),
    "Programa de Reciclaje y Sostenibilidad": ("Compromiso con la sostenibilidad y el reciclaje ambiental." , "Otros"),
    "Centro de Arte y Cultura": ("Exposiciones de arte y eventos culturales en el hotel." , "Otros"),
    "Tienda de Regalos y Souvenirs": ("Compra de souvenirs y regalos en el lugar." , "Tienda"),
    "Servicio de Reserva de Teatro o Espectáculos": ("Ayuda para reservar entradas para eventos y espectáculos locales." , "Entretenimiento"),
    "Sala de Juegos": ("Área de juegos con billar, futbolín y otros juegos recreativos." , "Entretenimiento"),
    "Clases de Cocina": ("Lecciones de cocina para aprender a preparar platos locales." , "Entretenimiento"),
    "Servicio de Canguro": ("Cuidado de niños a cargo de profesionales para que los padres disfruten de tiempo libre." , "Guardería"),
    "Programas de Bienestar y Salud": ("Actividades como yoga y meditación para el bienestar de los huéspedes." , "Deporte"),
    "Alquiler de Equipo de Golf": ("Equipos de golf y acceso a campos de golf cercanos para los amantes de este deporte." , "Deporte")
}

# services = [
#     "Wi-Fi gratuito", "Estacionamiento", "Desayuno buffet", "Piscina al aire libre", "Gimnasio",
#     "Servicio de habitaciones", "Recepción 24 horas", "Restaurante en el lugar", "Servicio de lavandería",
#     "Servicio de transporte al aeropuerto"
# ]

# consumptions = [
#     "Almuerzo en el restaurante del hotel", "Cena en el restaurante del hotel", "Desayuno en la habitación",
#     "Minibar", "Llamadas telefónicas internacionales", "Servicio de lavandería", "Servicio de tintorería",
#     "Internet en la habitación", "Servicio de habitaciones", "Estacionamiento", "Alquiler de coche", "Excursiones", "Spa y masajes",
#     "Servicio de niñera", "Consumo de la tienda de regalos", "Alquiler de bicicletas", "Uso del gimnasio",
#     "Cobro por películas en la habitación", "Servicio de despertador", "Llamadas locales", "Servicio de impresión y fax",
#     "Tarifa de resort", "Impuestos y tasas", "Botellas de agua en la habitación", "Servicio de transporte al aeropuerto",
#     "Conferencias y reuniones", "Impresión de documentos", "Compras en el minibar", "Room service de madrugada",
#     "Consumo del bar del hotel", "Salida tardía", "Cargo por llave perdida", "Uso del centro de negocios",
#     "Uso del centro de convenciones", "Uso de la piscina cubierta", "Caja fuerte en la habitación", "Artículos de tocador de lujo",
#     "Servicio de manicura y pedicura", "Alquiler de equipos deportivos", "Periódico en la habitación", "Consumo de alimentos para mascotas",
#     "Servicio de transporte local", "Uso de la sala de juegos", "Cambio de moneda", "Alquiler de equipos de esquí",
#     "Paseos en barco", "Servicio de catering para eventos", "Depósito por objetos de valor",
# ]



def username(name, lastname):
    username = name[:3] + lastname[:3] + str(random.randint(1, 99999))
    return username

def password(length):
    characters = string.ascii_letters + string.digits + string.punctuation
    password = ''.join(random.choice(characters) for _ in range(length))
    return password.replace("'", "").replace("`", "").replace(",", "").replace(":", "")

def document():
    document = ''.join(str(random.randint(0, 9)) for _ in range(10))
    return document

def generateDataServicios():
    with open("Servicios.csv", "w") as file:
        sys.stdout = file
        for i in range(len(services)):
            servicio = list(services.items())[i]
            data = servicio[0] + ","
            data = data + servicio[1][0]
            data = data + ","
            data = data + str(random.randint(10000, 100000))
            data = data + ","
            data = data + str(random.randint(1, 10))
            data = data + ","
            data = data + random.choice([ "Diurno", "Nocturno", "Matutino", "Vespertino", "Tarde", "Anochecer", "Madrugada", "Atardecer", "Medianoche", "Hora punta", "Amanecer",])
            data = data + ","
            data = data + servicio[1][1]
            data = data + ","
            data = data + str(random.randint(3, 15))
            data = data + ","
            data = data + str(i + 1)
            print(data)

    with open("Servicios.csv", "rb+") as file:
        file.seek(-1, os.SEEK_END)
        file.truncate()

def generateSQLServicios():
    with open("Servicios.sql", "w") as file:
        sys.stdout = file
        for i in range(len(services)):
            servicio = list(services.items())[i]
            data = "INSERT INTO SERVICIOS (NOMBRE, DESCRIPCION, COSTOPORUNIDAD, UNIDAD, HORARIO, TIPOSERVICIO, CAPACIDAD, ID) VALUES (" + "'" + servicio[0] + "'" + ","
            data = data + "'" + servicio[1][0] + "'"
            data = data + ","
            data = data + str(random.randint(10000, 100000))
            data = data + ","
            data = data + str(random.randint(1, 10))
            data = data + ","
            data = data + "'" + random.choice([ "Diurno", "Nocturno", "Matutino", "Vespertino", "Tarde", "Anochecer", "Madrugada", "Atardecer", "Medianoche", "Hora punta", "Amanecer",]) + "'"
            data = data + ","
            data = data + "'" + servicio[1][1] + "'"
            data = data + ","
            data = data + str(random.randint(3, 15))
            data = data + ","
            data = data + "serviciossecuencia.nextval"
            data = data + ");"
            print(data)

def generateDataUsuarios(num):
    with open("Usuarios.csv", "w") as file:
        sys.stdout = file
        for i in range(num):
            data = str(i + 1) + ","
            name = random.choice(names)
            lastname = random.choice(lastnames) 
            data = data + username(name, lastname)
            data = data + ","
            data = data + password(random.randint(8, 16))
            data = data + ","
            data = data + name + " " + lastname
            data = data + ","
            data = data + document()
            data = data + ","
            data = data + name.lower() + lastname[0:random.randint(0, len(lastname))].lower() + (str(random.randint(0, 9999)) if random.choice([True, True, False]) else "") + "@" + random.choice(["gmail", "yahoo", "hotmail", "outlook"]) + ".com"
            data = data + ","
            data = data + random.choice(["Cedula", "Tarjeta Identidad"])
            data = data + ","
            data = data + str(random.randint(1, 5)) # TIPODEUSUARIO_ID
            print(data)

    with open("Usuarios.csv", "rb+") as file:
        file.seek(-1, os.SEEK_END)
        file.truncate()

def generateSQLUsuarios(num):
    with open("Usuarios.sql", "w") as file:
        sys.stdout = file
        for i in range(num):
            data = "INSERT INTO USUARIOS (ID, NOMBREUSUARIO, CONTRASENA, NOMBRE, NUMDOCUMENTO, EMAIL, TIPODOCUMENTO, TIPOSDEUSUARIO_ID) VALUES (" + "usuariossecuencia.nextval" + ","
            name = random.choice(names)
            lastname = random.choice(lastnames)
            data = data + "'" + username(name, lastname) + "'"
            data = data + ","
            data = data + "'" + password(random.randint(8, 16)) + "'"
            data = data + ","
            data = data + "'" + name + " " + lastname + "'"
            data = data + ","
            data = data + document()
            data = data + ","
            data = data + "'" + name.lower() + lastname[0:random.randint(0, len(lastname))].lower() + (str(random.randint(0, 9999)) if random.choice([True, True, False]) else "") + "@" + random.choice(["gmail", "yahoo", "hotmail", "outlook"]) + ".com" + "'"
            data = data + ","
            data = data + "'" + random.choice(["Cedula", "Tarjeta Identidad"]) + "'"
            data = data + ","
            data = data + str(random.randint(1, 5)) # TIPODEUSUARIO_ID
            data = data + ");"
            print(data)

    with open("Usuarios.sql", "rb+") as file:
        file.seek(-1, os.SEEK_END)
        file.truncate()

def generateDataHabitaciones(pisos, habitacionesxpiso):
    with open("Habitaciones.csv", "w") as file:
        sys.stdout = file
        for i in range(1, pisos + 1):
            for j in range(1, habitacionesxpiso + 1):
                data = str(i) + ","
                data = data + str(random.randint(1, 6))
                data = data + ","
                data = data + str(random.randint(120000, 980000))
                data = data + ","
                data = data + str(i) + (str(0) if j < 10 else "") + str(j)
                data = data + ","
                data = data + str(random.randint(1, 3)) # TIPOSHABITACION_ID
                print(data)
    
    with open("Habitaciones.csv", "rb+") as file:
        file.seek(-1, os.SEEK_END)
        file.truncate()

def generateSQLHabitaciones(pisos, habitacionesxpiso):
    with open("Habitaciones.sql", "w") as file:
        sys.stdout = file
        for i in range(1, pisos + 1):
            for j in range(1, habitacionesxpiso + 1):
                data = "INSERT INTO HABITACIONES (ID, CAPACIDAD, COSTOALOJAMIENTO, NUMERO, TIPOSHABITACIONES_ID) VALUES (" + "habitacionessecuencia.nextval" + ","
                data = data + str(random.randint(1, 6))
                data = data + ","
                data = data + str(random.randint(120000, 980000))
                data = data + ","
                data = data + str(i) + (str(0) if j < 10 else "") + str(j)
                data = data + ","
                data = data + str(random.randint(1, 3)) # TIPOSHABITACION_ID
                data = data + ");"
                print(data)
    
    with open("Habitaciones.sql", "rb+") as file:
        file.seek(-1, os.SEEK_END)
        file.truncate()

def generateDataReservasHabitaciones(num, rooms, users):
    with open("ReservasHabitaciones.csv", "w") as file:
        sys.stdout = file
        for i in range(num):
            data = str(i + 1) + ","
            data = data + str(random.randint(1, 6))
            data = data + ","
            data = data + str(random.randint(1, 28)) + "/" + str(random.randint(1, 6)) + "/" + str(random.randint(2020, 2021))
            data = data + ","
            data = data + str(random.randint(1, 28)) + "/" + str(random.randint(6, 12)) + "/" + str(random.randint(2021, 2022))
            data = data + ","
            data = data + str(random.randint(1, 28)) + "/" + str(random.randint(1, 6)) + "/" + str(random.randint(2020, 2021))
            data = data + ","
            data = data + str(random.randint(1, 28)) + "/" + str(random.randint(6, 12)) + "/" + str(random.randint(2021, 2022))
            data = data + ","
            data = data + str(random.randint(1, rooms))
            data = data + ","
            data = data + str(random.randint(1, users))
            data = data + ","
            data = data + str(random.randint(1, 2)) # PLANESDECONSUMO_ID
            print(data)

    with open("ReservasHabitaciones.csv", "rb+") as file:
        file.seek(-1, os.SEEK_END)
        file.truncate()

def generateSQLReservasHabitaciones(num, rooms, users):
    with open("ReservasHabitaciones.sql", "w") as file:
        sys.stdout = file
        for i in range(num):
            data = "INSERT INTO RESERVASHABITACIONES (ID, NUMPERSONAS, FECHAINICIO, FECHAFIN, FECHACHECKIN, FECHACHECKOUT, HABITACIONES_ID, USUARIOS_ID, PLANESDECONSUMO_ID) VALUES (" + "reservashabitacionessecuencia.nextval" + ","
            data = data + str(random.randint(1, 6))
            data = data + ","
            data = data + "TO_DATE('" + str(random.randint(1, 28)) + "/" + str(random.randint(1, 6)) + "/" + str(random.randint(2020, 2021)) + "', 'DD/MM/YYYY')"
            data = data + ","
            data = data + "TO_DATE('" + str(random.randint(1, 28)) + "/" + str(random.randint(6, 12)) + "/" + str(random.randint(2021, 2022)) + "', 'DD/MM/YYYY')"
            data = data + ","
            data = data + "TO_DATE('" + str(random.randint(1, 28)) + "/" + str(random.randint(1, 6)) + "/" + str(random.randint(2020, 2021)) + "', 'DD/MM/YYYY')"
            data = data + ","
            data = data + "TO_DATE('" + str(random.randint(1, 28)) + "/" + str(random.randint(6, 12)) + "/" + str(random.randint(2021, 2022)) + "', 'DD/MM/YYYY')"
            data = data + ","
            data = data + str(random.randint(1, rooms))
            data = data + ","
            data = data + str(random.randint(1, users))
            data = data + ","
            data = data + str(random.randint(1, 2)) # PLANESDECONSUMO_ID
            data = data + ");"
            print(data)

    with open("ReservasHabitaciones.sql", "rb+") as file:
        file.seek(-1, os.SEEK_END)
        file.truncate()

def generateDataReservasServicios(num, users):
    with open("ReservasServicios.csv", "w") as file:
        sys.stdout = file
        for i in range(num):
            data = str(i + 1) + ","
            data = data + str(random.randint(1, 6))
            data = data + ","
            data = data + str(random.randint(1, 28)) + "/" + str(random.randint(1, 6)) + "/" + str(random.randint(2020, 2021))
            data = data + ","
            data = data + str(random.randint(1, 28)) + "/" + str(random.randint(6, 12)) + "/" + str(random.randint(2021, 2022))
            data = data + ","
            data = data + str(random.randint(1, users))
            data = data + ","
            data = data + str(random.randint(1, 50)) # SERVICIOS_ID
            print(data)

    with open("ReservasServicios.csv", "rb+") as file:
        file.seek(-1, os.SEEK_END)
        file.truncate()

def generateSQLReservasServicios(num, users):
    with open("ReservasServicios.sql", "w") as file:
        sys.stdout = file
        for i in range(num):
            data = "INSERT INTO RESERVASSERVICIOS (ID, NUMPERSONAS, FECHAINICIO, FECHAFIN, USUARIOS_ID, SERVICIOS_ID) VALUES (" + "reservasserviciossecuencia.nextval" + ","
            data = data + str(random.randint(1, 6))
            data = data + ","
            data = data + "TO_DATE('" + str(random.randint(1, 28)) + "/" + str(random.randint(1, 6)) + "/" + str(random.randint(2020, 2021)) + "', 'DD/MM/YYYY')"
            data = data + ","
            data = data + "TO_DATE('" + str(random.randint(1, 28)) + "/" + str(random.randint(6, 12)) + "/" + str(random.randint(2021, 2022)) + "', 'DD/MM/YYYY')"
            data = data + ","
            data = data + str(random.randint(1, users))
            data = data + ","
            data = data + str(random.randint(1, 50)) # SERVICIOS_ID
            data = data + ");"
            print(data)

    with open("ReservasServicios.sql", "rb+") as file:
        file.seek(-1, os.SEEK_END)
        file.truncate()

def generateDataConsumos(num, roomReservations, users):
    with open("Consumos.csv", "w") as file:
        sys.stdout = file
        for i in range(num):
            data = str(random.randint(100000, 1000000)) + ","
            data = data + str(random.randint(1, roomReservations))
            data = data + ","
            data = data + str(random.randint(1, 50))
            data = data + ","
            servicio = str(random.randint(1, 50))
            data = data + servicio
            data = data + ","
            data = data + str(i + 1)
            data = data + ","
            data = data + list(services.keys())[int(servicio) - 1]
            data = data + ","
            data = data + str(random.randint(1, 28)) + "/" + str(random.randint(1, 12)) + "/" + str(random.randint(2020, 2021))
            data = data + ","
            data = data + str(random.randint(1, users))
            print(data)

    with open("Consumos.csv", "rb+") as file:
        file.seek(-1, os.SEEK_END)
        file.truncate()

def generateSQLConsumos(num, roomReservations, users):
    with open("Consumos.sql", "w") as file:
        sys.stdout = file
        for i in range(num):
            data = "INSERT INTO CONSUMOS (SUMATOTAL, RESERVASHABITACIONES_ID, NUMCONSUMOS, SERVICIOS_ID, ID, NOMBRE, FECHACONSUMO, USUARIOS_ID) VALUES (" + str(random.randint(100000, 1000000)) + ","
            data = data + str(random.randint(1, roomReservations))
            data = data + ","
            data = data + str(random.randint(1, 50))
            data = data + ","
            servicio = str(random.randint(1, 50))
            data = data + servicio
            data = data + ","
            data = data + "consumossecuencia.nextval"
            data = data + ","
            data = data + "'" + list(services.keys())[int(servicio) - 1] + "'"
            data = data + ","
            data = data + "TO_DATE('" + str(random.randint(1, 28)) + "/" + str(random.randint(1, 12)) + "/" + str(random.randint(2020, 2021)) + "', 'DD/MM/YYYY')"
            data = data + ","
            data = data + str(random.randint(1, users))
            data = data + ");"
            print(data)

    with open("Consumos.sql", "rb+") as file:
        file.seek(-1, os.SEEK_END)
        file.truncate()

users = int(input("Number of users: "))
floors = int(input("Number of floors: "))
roomsPerFloor = int(input("Number of rooms per floor: "))
roomReservations = int(input("Number of room reservations: "))
serviceReservations = int(input("Number of service reservations: "))
consumption = int(input("Number of consumptions: "))

generateDataServicios()
generateSQLServicios()
generateDataUsuarios(users)
generateSQLUsuarios(users)
generateDataHabitaciones(floors, roomsPerFloor)
generateSQLHabitaciones(floors, roomsPerFloor)
generateDataReservasHabitaciones(roomReservations, floors * roomsPerFloor, users)
generateSQLReservasHabitaciones(roomReservations, floors * roomsPerFloor, users)
generateDataReservasServicios(serviceReservations, users)
generateSQLReservasServicios(serviceReservations, users)
generateDataConsumos(consumption, roomReservations, users)
generateSQLConsumos(consumption, roomReservations, users)