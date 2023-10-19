package example.객체연관관계;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class 하위클래스 {
    private int data;
    @ToString.Exclude
    private 상위클래스 상위객체;
}
