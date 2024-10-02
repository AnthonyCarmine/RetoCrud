# Coupon Campaigner

Coupon Campaigner es una aplicación de gestión de campañas y empresas se espera implementar con cupones y esta desarrollada con Java y Spring Boot.

## Requisitos

- Java 11 o superior
- Maven 3.6.0 o superior
- MySQL

## Configuración del Proyecto

1. Clona el repositorio:

    ```bash
    git clone https://github.com/tu-usuario/campaign-manager.git
    cd campaign-manager
    ```

2. Configura la base de datos MySQL:

    ```sql
    -- Create the database
    CREATE DATABASE campaign_manager;

    -- Use the database
    USE campaign_manager;

    -- Create the companies table with nit as the primary key
    CREATE TABLE companies (
        nit VARCHAR(50) PRIMARY KEY,
        name VARCHAR(255) NOT NULL
    );

    -- Create the campaigns table
    CREATE TABLE campaigns (
        id INT AUTO_INCREMENT PRIMARY KEY,
        name VARCHAR(255) NOT NULL,
        start_date DATE,
        end_date DATE,
        company_nit VARCHAR(50),
        FOREIGN KEY (company_nit) REFERENCES companies(nit)
    );

    -- Insert 5 Colombian companies
    INSERT INTO companies (nit, name) VALUES
    ('800197268', 'Bavaria S.A.'),
    ('840007336', 'Ecopetrol S.A.'),
    ('830002964', 'Grupo Aval Acciones y Valores S.A.'),
    ('820007386', 'Bancolombia S.A.'),
    ('810002964', 'Almacenes Éxito S.A.');
    ```

3. Configura las propiedades de la base de datos en `src/main/resources/application.properties`:

    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/campaign_manager
    spring.datasource.username=tu-usuario
    spring.datasource.password=tu-contraseña
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true
    ```

## Ejecución del Proyecto

1. Compila y ejecuta el proyecto con Maven:

    ```bash
    mvn clean install
    mvn spring-boot:run
    ```

2. La aplicación estará disponible en `http://localhost:8080`.

## Endpoints

### Campañas

1. **Obtener todas las campañas**
   - **URL**: `GET /campaigns/AllCampaigns`
   - **Descripción**: Obtiene una lista de todas las campañas.

2. **Obtener una campaña por ID**
   - **URL**: `GET /campaigns/Campaign/{id}`
   - **Descripción**: Obtiene una campaña específica por su ID.
   - **Parámetros**:
     - `id` (Path Variable): El ID de la campaña.

3. **Crear una nueva campaña**
   - **URL**: `POST /campaigns/createCampaign`
   - **Descripción**: Crea una nueva campaña.
   - **Body**:
     ```json
     {
         "name": "New Campaign",
         "startDate": "2023-01-01",
         "endDate": "2023-12-31",
         "companyNit": "800197268"
     }
     ```

4. **Actualizar una campaña existente**
   - **URL**: `PUT /campaigns/updateCampaign/{id}`
   - **Descripción**: Actualiza una campaña existente.
   - **Parámetros**:
     - `id` (Path Variable): El ID de la campaña.
   - **Body**:
     ```json
     {
         "name": "Updated Campaign",
         "startDate": "2023-01-01",
         "endDate": "2023-12-31",
         "companyNit": "800197268"
     }
     ```

5. **Eliminar una campaña**
   - **URL**: `DELETE /campaigns/deleteCampaign/{id}`
   - **Descripción**: Elimina una campaña específica por su ID.
   - **Parámetros**:
     - `id` (Path Variable): El ID de la campaña.

### Empresas

1. **Obtener todas las empresas**
   - **URL**: `GET /companies/AllCompanies`
   - **Descripción**: Obtiene una lista de todas las empresas.

2. **Obtener una empresa por NIT o nombre**
   - **URL**: `GET /companies/Company/{identifier}`
   - **Descripción**: Obtiene una empresa específica por su NIT o nombre.
   - **Parámetros**:
     - `identifier` (Path Variable): El NIT o nombre de la empresa.


