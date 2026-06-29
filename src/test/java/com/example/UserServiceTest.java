package com.example;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;

import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.anyString;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    @Test
    void findUser_shouldReturnUser_whenUserExists(){
        //Arrange
        when(userRepository.findById(1)).thenReturn(Optional.of("Manga"));

        //Act
       String result = userService.getUserName(1);

       //Assert
        assertEquals("Manga", result);
    }
    @Test
    void findUser_shouldReturnGuest_whenUserNotFound() {
        when(userRepository.findById(2)).thenReturn(Optional.empty());
        String result = userService.getUserName(2);
        assertEquals("Guest", result);
    }
    @Test
    void saveUser_shouldCallRepository(){
        userService.register("Manga", "manga.mango@example.com");
        verify(userRepository).save("Manga");
    }
    @Test
    void register_shouldReject_whenUsernameIsNull() {
        assertThrows(IllegalArgumentException.class,
                () -> userService.register(null, "test@email.com"));
    }

    @Test
    void register_shouldNeverSave_whenUsernameIsInvalid(){

        //Act & Assert
        assertThrows(IllegalArgumentException.class,
                ()-> userService.register("M","test@email.com"));
        verify(userRepository, never()).save(anyString());
    }
    @Test
    void register_shouldThrowException_whenEmailIsTaken(){
        // Arrange
        when(userRepository.existsByEmail("manga.mango@example.com")).thenReturn(true);

        // Act & Assert
        assertThrows(IllegalStateException.class,
                ()-> userService.register("Amber", "manga.mango@example.com"));
    }
    @ParameterizedTest
    @ValueSource(strings = {"", "M", "\t", "\n"})
    void invalidUsername_shouldBeRejected(String input) {
        assertThrows(IllegalArgumentException.class,
                () -> userService.register(input, "test@email.com"));
    }

    @ParameterizedTest (name = "alice should register")
    @ValueSource(strings = { "alice", "bob123", "x_y"})
    void validUsername_shouldAccepts (String username){
        userService.register(username, "unique@email.com");
        verify(userRepository).save(username);
    }

}
