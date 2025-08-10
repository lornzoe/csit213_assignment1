public class Person
{
    private String name;
    private String nric;
    private String gender;
    private String dateOfBirth;
    
    public Person(String name, String nric, String gender, String dateOfBirth) 
    {
        this.name = name;
        this.nric = nric;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
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
        return name + " (" + nric + ")";
    }
}