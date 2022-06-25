package dev.community.gdg.chatting.room.domain;

import dev.community.gdg.member.domain.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class ChattingRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "firstMemberId")
    private Member firstMember;

    @ManyToOne
    @JoinColumn(name = "secondMemberId")
    private Member secondMember;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    public static ChattingRoom of(Member firstMember, Member secondMember) {
        ChattingRoom chattingRoom = new ChattingRoom();
        chattingRoom.firstMember = firstMember;
        chattingRoom.secondMember = secondMember;
        return chattingRoom;
    }
}
