package JasonLee.HelloSpring.repository;

import JasonLee.HelloSpring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;
    //The 0L means the number zero of type long

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(),member);
        return member;
    }
    //save 할때 마다 sequence값 하나씩 추가.
    //그 값을 멤버 아이디로 해서 그 아이디와 멤버인스턴스로 짝지어진 쌍을 store에 저장.
    //리턴은 그냥 그 멤버 자체.

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }
    //null이면 empty Optional 리턴, 값이 있으면 그 값 그대로 리턴

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }
    //store에 저장된 값들에서 람다함수의 결과값에 맞는걸 필터링.

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }
    //해시맵이 아닌 리스트를 반환하기위해 해시맵의 값만으로 이루어진 어레이리스트를 만들어서 반환.

    public void clearStore(){
        store.clear();
    }
}
