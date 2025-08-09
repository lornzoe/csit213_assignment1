public class Person
{
    public String name;
    public String nric;
    public String gender;
    public String dateOfBirth;
    
    public Person(String n, String nr, String g, String dob) 
    {
        this.name = n;
        this.nric = nr;
        this.gender = g;
        this.dateOfBirth = dob;
    }

    public String getName()
    {
        return name;
    }

    public String getNric()
    {
        return nric;
    }

    public String getGender()
    {
        return gender;
    }

    public String getDateOfBirth()
    {
        return dateOfBirth;
    }

    @Override
    public String toString()
    {
        return "";
    }
}