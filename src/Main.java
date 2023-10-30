import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.io.IOException;
import java.util.regex.Pattern;
import java.util.stream.*;


class Main {
    static List<Estudiante>estudiantes;
    public static void main(String[] args) throws IOException {
        cargarArchivo();
        mostrarAspirantesPorCarrera();
        mostrarTotalMujeresPorCarrera();
        mostrarTotalHombresPorCarrera();
        PuntajeMasAltoPorCarrera();
        PuntajeMasAltoDeTodos();
        PuntajePromedioPorCarrera();
    }

    static void cargarArchivo() throws IOException{
        Pattern pattern =Pattern.compile(",");
        String filename= "student-scores.csv";

        try(Stream<String> lines = Files.lines(Path.of(filename))){
            estudiantes=lines.skip(1).map(line->{
                String[]arr=pattern.split(line);
                return new Estudiante(arr[0],arr[1],arr[2],arr[4],arr[9],Integer.parseInt(arr[10]));
            }).collect(Collectors.toList());
            estudiantes.forEach(System.out::println);
        }
    }

    static void mostrarAspirantesPorCarrera(){
        System.out.printf("%nAspirantes por carrera:%n");
        Map<String, List<Estudiante>> agrupadoPorCarrera = estudiantes.stream().collect(Collectors.groupingBy(Estudiante::getCareer_aspiration));
        agrupadoPorCarrera.forEach((carrera, aspirantesPorCarrera) ->
                {
                    System.out.println(carrera);
                    aspirantesPorCarrera.forEach(estudiante -> System.out.printf(" %s%n", estudiante));
                    System.out.println("\u001B[36m" + "Total de estudiantes aspirantes para " + carrera + ": " + aspirantesPorCarrera.size() + "\u001B[0m");
                    System.out.println("------------------------------------");
                }
        );
    }

    static void mostrarTotalMujeresPorCarrera(){
        System.out.printf("%nMujeres por carrera:%n");
        Map<String, Long> conteoMujeresPorCarrera = estudiantes.stream().filter(estudiante -> estudiante.getGender().equals("female")).collect(Collectors.groupingBy(Estudiante::getCareer_aspiration,Collectors.counting()));
        conteoMujeresPorCarrera.forEach((carrera, conteo) -> System.out.printf("%s: %d mujeres%n", carrera, conteo));
    }

    static void mostrarTotalHombresPorCarrera() {
        System.out.printf("%nHombres por carrera:%n");
        Map<String, Long> conteoHombresPorCaarrera = estudiantes.stream().filter(estudiante -> estudiante.getGender().equals("male")).collect(Collectors.groupingBy(Estudiante::getCareer_aspiration,Collectors.counting()));
        conteoHombresPorCaarrera.forEach((carrera, conteo) -> System.out.printf("%s: %d hombres%n", carrera, conteo));
    }

    static void PuntajeMasAltoPorCarrera() {
        Function<Estudiante, Integer> porPuntaje = Estudiante::getMath_score;
        Comparator<Estudiante> PuntajeDescendete = Comparator.comparing(porPuntaje);
        System.out.printf("%nPuntaje más alto por carrera: %n");
        Map<String, List<Estudiante>> agrupadoPorCarrera = estudiantes.stream().collect(Collectors.groupingBy(Estudiante::getCareer_aspiration));
        agrupadoPorCarrera.forEach((carrera, estudiantesPorCarrera) -> {
            System.out.print(carrera+": ");
            Estudiante estudianteMas = estudiantesPorCarrera.stream().max(PuntajeDescendete).get();
            System.out.println(estudianteMas.getFirst_name()+" "+estudianteMas.getLast__name()+
            "/// Math Score "+estudianteMas.getMath_score());
                }
        );
    }

    static void PuntajeMasAltoDeTodos() {
        System.out.printf("%nPuntaje más alto de todos: %n");
        Function<Estudiante, Integer> porPuntaje = Estudiante::getMath_score;
        Comparator<Estudiante> PuntajeDescendete = Comparator.comparing(porPuntaje);
        Estudiante estudianteMas = estudiantes.stream().max(PuntajeDescendete).get();
        System.out.println(estudianteMas.getFirst_name() + " " + estudianteMas.getLast__name() + " obtuvo el puntaje más alto: " + estudianteMas.getMath_score());
    }

    static void PuntajePromedioPorCarrera() {
        System.out.printf("%nPuntaje más alto por carrera: %n");
        Map<String, Double> agrupadosPorCarrera = estudiantes.stream().collect(Collectors.groupingBy(Estudiante::getCareer_aspiration,Collectors.averagingDouble(Estudiante::getMath_score)));
        agrupadosPorCarrera.forEach((carrera, average) -> {
            System.out.printf("Promedio de " + carrera + " = %.4f %n" , average);
        });
    }
}
