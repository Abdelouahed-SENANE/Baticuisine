create type project__status as ENUM ('ONGOING' , 'ENDED' , 'CANCELLED')

create table if not exists projects (
    project_id UUID default uuid_generate_v4() primary key,
    project_name VARCHAR(255),
    profit_margin Double precision,
    overall_cost double precision,
    project_status project__status
)

create table if not exists estimates (
    estimate_id UUID default uuid_generate_v4() primary key,
    issue_at Timestamp,
    validaty_date Double precision,
    is_accepted BOOLEAN default false,
    )

create table if not exists customers (
    costumer_id UUID default uuid_generate_v4() primary key,
    fisrtname VARCHAR(255),
    address VARCHAR(255),
    phone_number VARCHAR(255),
    is_professional BOOLEAN default false,
    )

create table if not exists components(
     component_id UUID default uuid_generate_v4() primary key,
        component_name VARCHAR(255),
        vat double precision
    )

create table if not exists materials(
    price_per_unit double precision,
    quantity integer,
    transportation_cost Double precision,
    quality_coefficient Double precision
) INHERITS (components)
create table if not exists workforces(
    hourly_rate double precision,
    work_hours integer,
    worker_productivity_coefficient Double precision
)INHERITS (components)