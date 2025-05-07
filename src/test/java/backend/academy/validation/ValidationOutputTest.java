package backend.academy.validation;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ValidationOutputTest {
    ValidationOutput validationOutput = new ValidationOutput();

    @Test
    public void givenNotParseIntValue_whenTryReadNotParseIntValue_thenReturnFalse() {
        //given
        String number = "dsds";
        //when
        boolean result = validationOutput.validationNumber(number);
        //then
        assertThat(result).isFalse();
    }

    @Test
    public void givenMinusValue_whenTryReadMinusValue_thenReturnFalse() {
        //given
        String number = "-2";
        //when
        boolean result = validationOutput.validationNumber(number);
        //then
        assertThat(result).isFalse();
    }

    @Test
    public void givenIncorrectMazeGenerateAlgorithm_whenTryReadIncorrectMazeGenerateAlgorithm_thenReturnFalse() {
        //given
        String algorithm = "sdfsfa";
        //when
        boolean result = validationOutput.validationAlgorithm("src/main/resources/algorithms.txt", algorithm);
        //then
        assertThat(result).isFalse();
    }

    @Test
    public void givenIncorrectFindPathAlgorithm_whenTryReadIncorrectFindPathAlgorithm_thenReturnFalse() {
        //given
        String algorithm = "sdfsfa";
        //when
        boolean result = validationOutput.validationAlgorithm("src/main/resources/FindPathAlgorithm.txt", algorithm);
        //then
        assertThat(result).isFalse();
    }
}
