-- Add columns numberOfMotocyclesSpaces and numberOfCarSpaces to the company table
ALTER TABLE company
ADD COLUMN motocycles_spaces INT NOT NULL  DEFAULT 0,
ADD COLUMN cars_spaces INT NOT NULL  DEFAULT 0;

