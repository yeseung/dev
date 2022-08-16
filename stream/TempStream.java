import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class TempStream {

    public static void main(String[] args) {

        List<People> genderList = People.getPeopleList().stream().filter(people -> people.getGender() == 2).collect(Collectors.toList());
        System.out.println("여성목록");
        genderList.forEach(System.out::println);

        List<People> sortedList = People.getPeopleList().stream().sorted(Comparator.comparing(People::getAge)).collect(Collectors.toList());
        System.out.println("나이순 목록");
        sortedList.forEach(System.out::println);

        boolean allMatch = People.getPeopleList().stream().allMatch(people -> people.getAge() > 12);
        System.out.println("나이가 모두 12 보다 많은가?");
        System.out.println("allMatch = " + allMatch);

        boolean anyMatch = People.getPeopleList().stream().anyMatch(people -> people.getAge() > 19);
        System.out.println("나이가 19 보다 많은 사람이 있는가?");
        System.out.println("anyMatch = " + anyMatch);

        boolean noneMatch = People.getPeopleList().stream().noneMatch(people -> people.getAge() > 19);
        System.out.println("나이가 19 보다 많은 사람이 없는가?");
        System.out.println("noneMatch = " + noneMatch);

        Optional<People> maxPeople  = People.getPeopleList().stream().max(Comparator.comparing(People::getAge));
        if (maxPeople.isPresent()) {
            System.out.println("나이가 제일 많은 사람은?");
            System.out.println("maxPeople.get() = " + maxPeople.get());
        }

        Optional<People> minPeople  = People.getPeopleList().stream().min(Comparator.comparing(People::getAge));
        if (minPeople.isPresent()) {
            System.out.println("나이가 제일 적은 사람은?");
            System.out.println("minPeople.get() = " + minPeople.get());
        }

        Map<Integer, List<People>> groupByGender = People.getPeopleList().stream().collect(Collectors.groupingBy(People::getGender));
        System.out.println("남성/여성 목록");
        groupByGender.forEach((gender, peopleList) -> {
            System.out.println("gender = " + gender);
            peopleList.forEach(System.out::println);
        });

        Optional<String> s = People.getPeopleList().stream().filter(people -> people.getGender() == 2).max(Comparator.comparing(People::getAge)).map(People::getName);
        System.out.println("여성 나이가 많은 사람 이름?");
        s.ifPresent(System.out::println);

    }
}
