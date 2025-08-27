class CarparkDataException extends  Exception
{
    public CarparkDataException(String s){
        super(s);
    }

    public CarparkDataException(String message, Throwable cause) {
        super(message, cause);
    }
}