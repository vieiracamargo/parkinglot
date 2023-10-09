package exception;

import java.util.List;

public record ApiError(List<Message> errors) {
}
