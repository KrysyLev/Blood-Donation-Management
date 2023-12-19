-- Donor table
CREATE TABLE Donor (
  DonorID INT PRIMARY KEY ,
  FirstName VARCHAR(50),
  LastName VARCHAR(50),
  DateOfBirth DATE,
  Gender VARCHAR(10),
  Phone VARCHAR(20),
  Email VARCHAR(100),
  Address VARCHAR(100),
);

-- Staff table
CREATE TABLE Staff (
  StaffID INT PRIMARY KEY,
  FirstName VARCHAR(50),
  LastName VARCHAR(50),
  Phone VARCHAR(20),
  Email VARCHAR(100),
  Address VARCHAR(100),
  Role VARCHAR(50),
);

create table DonorPassword(
	DonorID int primary key,
	userName varchar(50),
	DonorPassword varchar(50),
	Foreign key (DonorID) references Donor(DonorID)
);

Create table StaffPassword(
	StaffID int primary key,
	userName varchar(50),
	StaffPassword varchar(50),
	Foreign key (StaffID) references Staff(StaffID)
);

-- BloodBank table
CREATE TABLE BloodBank (
  BloodBagID INT PRIMARY KEY,
  BloodType VARCHAR(10),
  RhFactor VARCHAR(10),
  ExpirationDate DATE,
  StorageLocation VARCHAR(50)
);

-- AdverseReactions table
CREATE TABLE AdverseReactions (
  ReactionID INT PRIMARY KEY IDENTITY(0,1),
  ReactionType VARCHAR(100),
  ReactionDate DATE DEFAULT GETDATE(),
  Severity VARCHAR(50),
);

-- MedicalHistory table
CREATE TABLE MedicalHistory (
  MedicalHistoryID INT PRIMARY KEY IDENTITY(0,1),
  DonorID INT,
  MedicalCondition VARCHAR(100),
  Medications VARCHAR(100),
  Allergies VARCHAR(100),
  RecentIllness VARCHAR(255),
  RecentTravel VARCHAR(255),
  Covid19Status VARCHAR(20),
  Status VARCHAR(20) DEFAULT 'Unidentified',  -- Default value set to 'Unidentified'
  MeetingDate DATETIME NULL,  -- Add MeetingDate field for scheduled blood donation date
  RecordedDate DATETIME DEFAULT GETDATE(),
  FOREIGN KEY (DonorID) REFERENCES Donor(DonorID)
);

	 
-- BloodDonation table
CREATE TABLE BloodDonation (
  DonationID INT PRIMARY KEY IDENTITY(0,1),
  BloodBagID INT,
  StaffID INT,
  DonorID INT,
  MedicalHistoryID INT,

  FOREIGN KEY (BloodBagID) REFERENCES BloodBank(BloodBagID),
  FOREIGN KEY (StaffID) REFERENCES Staff(StaffID),
  FOREIGN KEY (DonorID) REFERENCES Donor(DonorID),
  FOREIGN KEY (MedicalHistoryID) REFERENCES MedicalHistory(MedicalHistoryID)
);

-- Recipient table
CREATE TABLE Recipient (
  DonorID INT,
  DonationID INT,
  DonationDate DATETIME DEFAULT GETDATE(),
  DonationType VARCHAR(50),
  PRIMARY KEY (DonorID, DonationID),

  FOREIGN KEY (DonorID) REFERENCES Donor(DonorID),
  FOREIGN KEY (DonationID) REFERENCES BloodDonation(DonationID)
);





-- Blood Donation to Adverse Reactions relationship table
CREATE TABLE BloodDonationAdverseReactions (
  DonationID INT,
  ReactionID INT,
  PRIMARY KEY (DonationID, ReactionID),
  FOREIGN KEY (DonationID) REFERENCES BloodDonation(DonationID),
  FOREIGN KEY (ReactionID) REFERENCES AdverseReactions(ReactionID)
);


CREATE TRIGGER set_expiration_date
ON BloodBank
AFTER INSERT
AS
BEGIN
    UPDATE b
    SET ExpirationDate = DATEADD(DAY, 90, GETDATE())
    FROM BloodBank b
    INNER JOIN inserted i ON b.BloodBagID = i.BloodBagID;
END;