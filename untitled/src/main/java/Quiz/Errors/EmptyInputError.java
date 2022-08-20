package Quiz.Errors;

public class EmptyInputError implements AppError {

    private final String errorMessage;
    private final String inputFieldName;

    public EmptyInputError(String inputFieldName ,String errorMessage) {
        this.inputFieldName = inputFieldName;
        this.errorMessage = errorMessage;
    }
    
    @Override
    public String getErrorMessage() {
        return errorMessage;
    }
}
