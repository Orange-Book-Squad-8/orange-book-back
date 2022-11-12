package com.bookorange.api.configuration;

import com.bookorange.api.domain.*;
import com.bookorange.api.enumerator.ContentType;
import com.bookorange.api.enumerator.Difficulty;
import com.bookorange.api.enumerator.StackCategories;
import com.bookorange.api.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.List;

@Configuration
@EnableJpaRepositories("com.bookorange.api.repository")
@AllArgsConstructor
public class ApplicationConfiguration {

    AppUserRepository appUserRepository;
    CourseRepository courseRepository;
    LessonRepository lessonRepository;
    RoleRepository roleRepository;
    SectionRepository sectionRepository;


    @Bean
    public void initializeDatabase() {
        Lesson lesson1 = new Lesson();
        lesson1.setTitle("lessonTitle");
        lesson1.setDescription("lessonDescription");
        lesson1.setAuthor("lesson author");
        lesson1.setLink("https://dev.to/vitordevsp/padronizacao-de-commit-com-commitlint-husky-e-commitizen-3g1n");
        lesson1.setTopic("lesson topic");
        lesson1.setContentType(ContentType.ARTICLE);
        lesson1.setDurationInMinutes(5);

        Lesson lesson2 = new Lesson();
        lesson2.setTitle("lesson2 Title");
        lesson2.setDescription("lesson2 Description");
        lesson2.setAuthor("lesson2 author");
        lesson2.setLink("https://dev.to/vitordevsp/padronizacao-de-commit-com-commitlint-husky-e-commitizen-3g1n");
        lesson2.setTopic("lesson2 topic");
        lesson2.setContentType(ContentType.VIDEO);
        lesson2.setDurationInMinutes(2);

        lessonRepository.save(lesson1);
        lessonRepository.save(lesson2);


        Section section1 = new Section();
        section1.setName("section1 name");
        section1.setLessons(List.of(lesson1, lesson2));

        Section section2 = new Section();
        section2.setName("section2 name");
        section2.setLessons(List.of(lesson1, lesson2));

        sectionRepository.save(section1);
        sectionRepository.save(section2);

        Course course1 = new Course();
        course1.setTitle("Course 1 title");
        course1.setDescription("Course 1 description");
        course1.setCreator("creator 2");
        course1.setCategory(StackCategories.UI);
        course1.setDifficulty(Difficulty.ADVANCED);
        course1.setVisible(true);
        course1.setSections(List.of(section1, section2));

        courseRepository.save(course1);

        Section section3 = new Section();
        section3.setName("section3 name");
        section3.setLessons(List.of(lesson1, lesson2));

        Section section4 = new Section();
        section4.setName("section4 name");
        section4.setLessons(List.of(lesson1, lesson2));

        sectionRepository.save(section3);
        sectionRepository.save(section4);

        Course course2 = new Course();
        course2.setTitle("Course 2 title");
        course2.setDescription("Course 2 description");
        course2.setCreator("creator 2");
        course2.setCategory(StackCategories.FULLSTACK);
        course2.setDifficulty(Difficulty.BEGINNER);
        course2.setVisible(true);
        course2.setSections(List.of(section3, section4));


        courseRepository.save(course2);

        Section section5 = new Section();
        section5.setName("section5 name");
        section5.setLessons(List.of(lesson1, lesson2));

        Section section6 = new Section();
        section6.setName("section6 name");
        section6.setLessons(List.of(lesson1, lesson2));

        sectionRepository.save(section5);
        sectionRepository.save(section6);

        Course course3 = new Course();
        course3.setTitle("Course 3 title");
        course3.setDescription("Course 3 description");
        course3.setCreator("creator 3");
        course3.setCategory(StackCategories.FULLSTACK);
        course3.setDifficulty(Difficulty.BEGINNER);
        course3.setVisible(false);
        course3.setSections(List.of(section5, section6));

        courseRepository.save(course3);

        // ***************************************
        //              DATABASE
        // ROLE
        Role userRole = new Role();
        userRole.setName("user1");

        Role admRole = new Role();
        admRole.setName("admin");

        roleRepository.save(userRole);
        roleRepository.save(admRole);

        // APPUSER - ADMIN
        AppUser adm1 = new AppUser();
        adm1.setUsername("thiago_adm");
        adm1.setPassword("minhasenha123");
        adm1.setEmail("thiago@gmail.com");
        adm1.setStackCategories(List.of(StackCategories.FULLSTACK));
        adm1.setBadges(List.of("Java", "Spring Boot"));
        adm1.setRole(admRole);

        AppUser adm2 = new AppUser();
        adm2.setUsername("bianca_adm");
        adm2.setPassword("senhagrande123");
        adm2.setEmail("bianca@yahoo.com.br");
        adm2.setStackCategories(List.of(StackCategories.UI, StackCategories.UX));
        adm2.setBadges(List.of("Java", "Spring Boot"));
        adm2.setRole(admRole);

        appUserRepository.save(adm1);
        appUserRepository.save(adm2);

        //APP USER - USER
        AppUser user1 = new AppUser();
        user1.setUsername("leandro");
        user1.setPassword("coloqueiumasenha");
        user1.setEmail("leandro@terra.com.br");
        user1.setStackCategories(List.of(StackCategories.FRONT_END));
        user1.setBadges(List.of("JavaScript", "React"));
        user1.setRole(userRole);

        AppUser user2 = new AppUser();
        user2.setUsername("fernando");
        user2.setPassword("talvezsejaumaboasenha");
        user2.setEmail("fernando19@gmail.com.br");
        user2.setStackCategories(List.of(StackCategories.FULLSTACK));
        user2.setBadges(List.of("JavaScript", "React", "C#", "MySQL"));
        user2.setRole(userRole);

        AppUser user3 = new AppUser();
        user3.setUsername("alice");
        user3.setPassword("mesmasenhapratudo");
        user3.setEmail("alicenopais@gmail.com");
        user3.setStackCategories(List.of(StackCategories.BACK_END));
        user3.setBadges(List.of("Java", "Spring Boot", "Django", "Python"));
        user3.setRole(userRole);

        AppUser user4 = new AppUser();
        user4.setUsername("otto");
        user4.setPassword("basicamenteumasenha");
        user4.setEmail("ottolol@gmail.com");
        user4.setStackCategories(List.of(StackCategories.UI, StackCategories.FRONT_END));
        user4.setBadges(List.of("Adobe XD", "FIGMA", "React"));
        user4.setRole(userRole);

        AppUser user5 = new AppUser();
        user5.setUsername("aline");
        user5.setPassword("nomedopet2020");
        user5.setEmail("alinepeace@gmail.com");
        user5.setStackCategories(List.of(StackCategories.FULLSTACK, StackCategories.FRONT_END));
        user5.setBadges(List.of("Kotlin", "Delphi", "React"));
        user5.setRole(userRole);

        AppUser user6 = new AppUser();
        user6.setUsername("dudu");
        user6.setPassword("sep1999");
        user6.setEmail("dudsep@gmail.com");
        user6.setStackCategories(List.of(StackCategories.FRONT_END));
        user6.setBadges(List.of("React", "Javascript", "Angular"));
        user6.setRole(userRole);

        AppUser user7 = new AppUser();
        user7.setUsername("mariana");
        user7.setPassword("sobrenome99");
        user7.setEmail("mari_sobrenome@gmail.com");
        user7.setStackCategories(List.of(StackCategories.FULLSTACK));
        user7.setBadges(List.of("Cobol", "Kotlin", "Flutter"));
        user7.setRole(userRole);

        AppUser user8 = new AppUser();
        user8.setUsername("helena");
        user8.setPassword("senhadiferentevouesquecer");
        user8.setEmail("helena182@gmail.com");
        user8.setStackCategories(List.of(StackCategories.FRONT_END));
        user8.setBadges(List.of("React", "Php"));
        user8.setRole(userRole);

        appUserRepository.save(user1);
        appUserRepository.save(user2);
        appUserRepository.save(user3);
        appUserRepository.save(user4);
        appUserRepository.save(user5);
        appUserRepository.save(user6);
        appUserRepository.save(user7);
        appUserRepository.save(user8);

        //LESSONS
        Lesson lesson3 = new Lesson();
        lesson3.setTitle("Migração de Carreira");
        lesson3.setDescription("Artigo para direcionar quem está passando por estra transição e obter um direcionamento");
        lesson3.setAuthor("Orange Juice");
        lesson3.setLink("https://medium.com/orangejuicefc/guia-definitivo-de-como-migrar-para-ux-design-5-passos-para-virar-um-ux-1675f71796b4");
        lesson3.setTopic("Global");
        lesson3.setContentType(ContentType.ARTICLE);
        lesson3.setDurationInMinutes(6);

        lessonRepository.save(lesson3);

        Lesson lesson4 = new Lesson();
        lesson4.setTitle("Migração de Carreira");
        lesson4.setDescription("Artigo para direcionar quem está passando por estra transição e obter um direcionamento");
        lesson4.setAuthor("Orange Juice");
        lesson4.setLink("https://medium.com/orangejuicefc/design-thinking-e-carreira-como-migrei-de-psicologia-para-ux-design-cb79e8b47df5");
        lesson4.setTopic("Global");
        lesson4.setContentType(ContentType.ARTICLE);
        lesson4.setDurationInMinutes(5);

        lessonRepository.save(lesson4);

        Lesson lesson5 = new Lesson();
        lesson5.setTitle("Migração de Carreira");
        lesson5.setDescription("Artigo para direcionar quem está passando por estra transição e obter um direcionamento");
        lesson5.setAuthor("Orange Juice");
        lesson5.setLink("https://medium.com/orangejuicefc/de-advogada-a-desenvolvedora-um-relato-sobre-minha-migra%C3%A7%C3%A3o-de-carreira-e-dicas-para-quem-pretende-45ad5df833b5");
        lesson5.setTopic("Global");
        lesson5.setContentType(ContentType.ARTICLE);
        lesson5.setDurationInMinutes(6);

        lessonRepository.save(lesson5);

        Lesson lesson6 = new Lesson();
        lesson6.setTitle("Culture Code");
        lesson6.setDescription("Venha conhecer um pouco mais FCamara, nosso Culture Code e o porque do nosso sangue ser laranja, Live importantíssima! ");
        lesson6.setAuthor("Grupo FCamara");
        lesson6.setLink("https://www.youtube.com/watch?v=n0KH8dQSrv0");
        lesson6.setTopic("Global");
        lesson6.setContentType(ContentType.VIDEO);
        lesson6.setDurationInMinutes(82);

        lessonRepository.save(lesson6);


        Lesson lesson7 = new Lesson();
        lesson7.setTitle("Culture Code");
        lesson7.setDescription("O que são PO(Product Owner) e SM(Scrum Master) e porque de serem tão importantes");
        lesson7.setAuthor("Grupo FCamara");
        lesson7.setLink("https://www.youtube.com/watch?v=_ku7bY5GtIY");
        lesson7.setTopic("Global");
        lesson7.setContentType(ContentType.VIDEO);
        lesson7.setDurationInMinutes(73);

        lessonRepository.save(lesson7);

        Lesson lesson8 = new Lesson();
        lesson8.setTitle("Desenvolvimento");
        lesson8.setDescription("Os primeiros passos de quem está começando no desenvolvimento e precisa de um norte!");
        lesson8.setAuthor("Grupo FCamara");
        lesson8.setLink("https://www.youtube.com/watch?v=N78-5gHLzbE");
        lesson8.setTopic("Global");
        lesson8.setContentType(ContentType.VIDEO);
        lesson8.setDurationInMinutes(85);

        lessonRepository.save(lesson8);

        Lesson lesson9 = new Lesson();
        lesson9.setTitle("UX Desing");
        lesson9.setDescription("Mas o que é UX? Descubra por aqui");
        lesson9.setAuthor("Orange Juice");
        lesson9.setLink("https://medium.com/orangejuicefc/mas-o-que-%C3%A9-ux-330edd9c6887");
        lesson9.setTopic("UX/UI");
        lesson9.setContentType(ContentType.ARTICLE);
        lesson9.setDurationInMinutes(4);

        lessonRepository.save(lesson9);

        Lesson lesson10 = new Lesson();
        lesson10.setTitle("Liderança");
        lesson10.setDescription("Live da BTG Pactual sobre Liderança");
        lesson10.setAuthor("BTG Pactual");
        lesson10.setLink("https://www.youtube.com/watch?v=b9A34yUvzEc");
        lesson10.setTopic("Global");
        lesson10.setContentType(ContentType.VIDEO);
        lesson10.setDurationInMinutes(56);

        lessonRepository.save(lesson10);

        Lesson lesson11 = new Lesson();
        lesson11.setTitle("Soft Skill");
        lesson11.setDescription("Artigo da Alura sobre Soft Skills");
        lesson11.setAuthor("Alura");
        lesson11.setLink("https://www.alura.com.br/artigos/soft-skills-o-que-sao-e-os-beneficios-de-desenvolve-las");
        lesson11.setTopic("Soft Skills");
        lesson11.setContentType(ContentType.ARTICLE);
        lesson11.setDurationInMinutes(4);

        lessonRepository.save(lesson11);

        Lesson lesson12 = new Lesson();
        lesson12.setTitle("Comunicação");
        lesson12.setDescription("A importância da empatia, compaixão e comunicação para o desenvolvimento de soluções centradas no usuário");
        lesson12.setAuthor("Alura");
        lesson12.setLink("https://www.alura.com.br/artigos/soft-skills-o-que-sao-e-os-beneficios-de-desenvolve-las");
        lesson12.setTopic("UX/UI");
        lesson12.setContentType(ContentType.ARTICLE);
        lesson12.setDurationInMinutes(5);

        lessonRepository.save(lesson12);

        Lesson lesson13 = new Lesson();
        lesson13.setTitle("MVP");
        lesson13.setDescription("MVP: o que é e como construir um Produto Mínimo Viável");
        lesson13.setAuthor("PM3");
        lesson13.setLink("https://www.cursospm3.com.br/blog/mvp-o-que-e-e-como-construir-um-produto-minimo-viavel/");
        lesson13.setTopic("Global");
        lesson13.setContentType(ContentType.ARTICLE);
        lesson13.setDurationInMinutes(8);

        lessonRepository.save(lesson13);

        Lesson lesson14 = new Lesson();
        lesson14.setTitle("Teste de Usabilidade");
        lesson14.setDescription("Live da Orange juice para mostrar o quanto é importante a fase de testes, para gerar resultados e melhorias ao seu protótipo.");
        lesson14.setAuthor("Orange Juice");
        lesson14.setLink("https://www.youtube.com/watch?v=VMXF3EDXZT8");
        lesson14.setTopic("UX/UI");
        lesson14.setContentType(ContentType.VIDEO);
        lesson14.setDurationInMinutes(78);

        lessonRepository.save(lesson14);

        Lesson lesson15 = new Lesson();
        lesson15.setTitle("Desing Thinking");
        lesson15.setDescription("O que de fato é Design Thinking? Qual seu propósito? Quais suas etaps? E por que devemos utilizar esse método em nossos projetos?");
        lesson15.setAuthor("Grupo FCamara");
        lesson15.setLink("https://www.youtube.com/watch?v=3s9pWGsU02k");
        lesson15.setTopic("UX/UI");
        lesson15.setContentType(ContentType.VIDEO);
        lesson15.setDurationInMinutes(60);

        lessonRepository.save(lesson15);

        Lesson lesson16 = new Lesson();
        lesson16.setTitle("Qual a melhor linguagem de programação? Com William da Silva");
        lesson16.setDescription("Os profissionais da FCamara falam sobre a experiência como júnior, planos para o futuro profissional");
        lesson16.setAuthor("Orange Juice");
        lesson16.setLink("https://www.youtube.com/watch?v=qZ4ZKJSmf4k");
        lesson16.setTopic("Desenvolvimento");
        lesson16.setContentType(ContentType.VIDEO);
        lesson16.setDurationInMinutes(50);

        lessonRepository.save(lesson16);

        Lesson lesson17 = new Lesson();
        lesson17.setTitle("Lógica de programação e algoritmos");
        lesson17.setDescription("Aprender Algoritmos e Lógica de Programação não é difícil! O professor Gustavo Guanabara, com mais de 20 anos de experiência, vai mostrar os passos para aprender a programar em 15 aulas bem descontraídas.");
        lesson17.setAuthor("Curso em Vídeo");
        lesson17.setLink("https://www.youtube.com/playlist?list=PLHz_AreHm4dmSj0MHol_aoNYCSGFqvfXV");
        lesson17.setTopic("Desenvolvimento");
        lesson17.setContentType(ContentType.COURSE);
        lesson17.setDurationInMinutes(140);

        lessonRepository.save(lesson17);

        Lesson lesson18 = new Lesson();
        lesson18.setTitle("Instalar o Linux no Windows com o WSL");
        lesson18.setDescription("Os desenvolvedores podem aproveitar o Windows e o Linux ao mesmo tempo em um computador Windows.");
        lesson18.setAuthor("Windows");
        lesson18.setLink("https://learn.microsoft.com/pt-br/windows/wsl/install");
        lesson18.setTopic("Desenvolvimento");
        lesson18.setContentType(ContentType.COURSE);
        lesson18.setDurationInMinutes(20);

        lessonRepository.save(lesson18);

        Lesson lesson19 = new Lesson();
        lesson19.setTitle("Mini Curso Javascript do Zero");
        lesson19.setDescription("Aprenda lógica de programação com o nosso curso de JavaScript do Zero 100% Gratuito!");
        lesson19.setAuthor("Cubos Acadamey");
        lesson19.setLink("https://acesse.cubos.academy/minicurso-javascript?hsLang=pt");
        lesson19.setTopic("Desenvolvimento");
        lesson19.setContentType(ContentType.COURSE);
        lesson19.setDurationInMinutes(210);

        lessonRepository.save(lesson19);

        Lesson lesson20 = new Lesson();
        lesson20.setTitle("Curso de HTML, CSS e Bootstrap");
        lesson20.setDescription("Curso para iniciantes no FrontEnd");
        lesson20.setAuthor("Michelli Brito");
        lesson20.setLink("hhttps://www.youtube.com/playlist?list=PL8iIphQOyG-Cv3auRYoZtbvzJJrsvdMy-");
        lesson20.setTopic("Desenvolvimento");
        lesson20.setContentType(ContentType.ARTICLE);
        lesson20.setDurationInMinutes(113);

        lessonRepository.save(lesson20);

        Lesson lesson21 = new Lesson();
        lesson21.setTitle("Git e Github para iniciantes");
        lesson21.setDescription("Tudo que você precisa para começar a versionar seus arquivos e contribuir com a comunidade opensource.");
        lesson21.setAuthor("Udemy");
        lesson21.setLink("https://www.udemy.com/course/git-e-github-para-iniciantes/");
        lesson21.setTopic("Desenvolvimento");
        lesson21.setContentType(ContentType.COURSE);
        lesson21.setDurationInMinutes(20);

        lessonRepository.save(lesson21);

        Lesson lesson22 = new Lesson();
        lesson22.setTitle("HTTP:Desmitificando o protocolo da Web");
        lesson22.setDescription("Saiba tudo sobre o protocolo da Web, HTTP");
        lesson22.setAuthor("Alura");
        lesson22.setLink("https://www.alura.com.br/artigos/desmistificando-o-protocolo-http-parte-1");
        lesson22.setTopic("Desenvolvimento");
        lesson22.setContentType(ContentType.ARTICLE);
        lesson22.setDurationInMinutes(45);

        lessonRepository.save(lesson22);

        Lesson lesson23 = new Lesson();
        lesson23.setTitle("Design Para Quem Não é Designer: Princípios de Design\n" +
                "e Tipografia Para Iniciantes");
        lesson23.setDescription("Robin Williams, de maneira clara e didática, ensina que qualquer pessoa pode elaborar páginas com uma estética melhor");
        lesson23.setAuthor("Robin Williams");
        lesson23.setLink("https://www.amazon.com.br/dp/857416836X/ref=cm_sw_r_tw_dp_4AHBKZPKDK9J290N0T28?_encoding=UTF8&psc=1");
        lesson23.setTopic("Global");
        lesson23.setContentType(ContentType.BOOK);
        lesson23.setDurationInMinutes(216);

        lessonRepository.save(lesson23);

        Lesson lesson24 = new Lesson();
        lesson24.setTitle("Comunicação não violenta");
        lesson24.setDescription("Manual prático e didático que apresenta metodologia criada pelo autor, voltada para aprimorar os relacionamentos interpessoais e diminuir a violência no mundo.");
        lesson24.setAuthor("Marshall B. Rosenberg");
        lesson24.setLink("https://www.amazon.com.br/dp/8571838267/ref=cm_sw_r_tw_dp_PRXG1BFCP2ZYTXVCVH2Z?_encoding=UTF8&psc=1");
        lesson24.setTopic("Global");
        lesson24.setContentType(ContentType.BOOK);
        lesson24.setDurationInMinutes(288);

        lessonRepository.save(lesson24);

        Lesson lesson25 = new Lesson();
        lesson25.setTitle("Arrume a  sua cama");
        lesson25.setDescription("Arrume a sua cama: Pequenas coisas que podem mudar a sua vida... E talvez o mundo ");
        lesson25.setAuthor("William H. McRaven");
        lesson25.setLink("https://www.amazon.com.br/dp/8542215788/ref=cm_sw_r_tw_dp_CP0QF5VZAEJDP8T5PGGF?_encoding=UTF8&psc=1");
        lesson25.setTopic("Global");
        lesson25.setContentType(ContentType.BOOK);
        lesson25.setDurationInMinutes(128);

        lessonRepository.save(lesson25);

        Lesson lesson26 = new Lesson();
        lesson26.setTitle("O design do dia a dia");
        lesson26.setDescription("Por que alguns produtos satisfazem os consumidores, enquanto outros os deixam completamente frustrados?");
        lesson26.setAuthor("William H. McRaven");
        lesson26.setLink("https://www.amazon.com.br/dp/8532520839/ref=cm_sw_r_tw_dp_0A8Q2AJE82HQ6S4MVFC3?_encoding=UTF8&psc=1");
        lesson26.setTopic("Global");
        lesson26.setContentType(ContentType.BOOK);
        lesson26.setDurationInMinutes(272);

        lessonRepository.save(lesson26);

        //SECTION

        Section section8 = new Section();
        section8.setName("Global");
        section8.setLessons(List.of(lesson3, lesson4, lesson5, lesson6, lesson7));

        Section section9 = new Section();
        section9.setName("Livros");
        section9.setLessons(List.of(lesson23, lesson24, lesson25, lesson26));

        sectionRepository.save(section8);
        sectionRepository.save(section9);

        Section section10 = new Section();
        section10.setName("Desenvolvimento");
        section10.setLessons(List.of(lesson16, lesson17, lesson18, lesson19, lesson20, lesson21, lesson22));

        Section section11 = new Section();
        section11.setName("UI/UX");
        section11.setLessons(List.of(lesson8, lesson9, lesson10, lesson11, lesson12, lesson13, lesson14, lesson15));

        sectionRepository.save(section10);
        sectionRepository.save(section11);

        //COURSE
        Course course4 = new Course();
        course4.setTitle("Trilha FullStack");
        course4.setDescription("Trilha direcionada a quem quer dar seus primeiros passos nessa jornada, de forma bem completa.Boa Viagem!");
        course4.setCreator("Orange Juice");
        course4.setCategory(StackCategories.FULLSTACK);
        course4.setDifficulty(Difficulty.BEGINNER);
        course4.setVisible(true);
        course4.setSections(List.of(section8, section10));

        courseRepository.save(course4);


        Section section12 = new Section();
        section11.setName("UI/UX");
        section11.setLessons(List.of(lesson8, lesson9, lesson10, lesson11, lesson12, lesson13, lesson14, lesson15));

        Section section13 = new Section();
        section13.setName("Global");
        section13.setLessons(List.of(lesson3, lesson4, lesson5, lesson6, lesson7));

        sectionRepository.save(section12);
        sectionRepository.save(section13);

        Course course5 = new Course();
        course5.setTitle("Trilha UI/UX");
        course5.setDescription("Trilha direcionada a quem quer dar seus primeiros passos nessa jornada, de forma bem completa.Boa Viagem!");
        course5.setCreator("Orange Juice");
        course5.setCategory(StackCategories.UI);
        course5.setDifficulty(Difficulty.BEGINNER);
        course5.setVisible(true);
        course5.setSections(List.of(section12, section13));

        courseRepository.save(course5);

        Section section14 = new Section();
        section14.setName("Global");
        section14.setLessons(List.of(lesson3, lesson4, lesson5, lesson6, lesson7));

        Section section15 = new Section();
        section15.setName("Livros");
        section15.setLessons(List.of(lesson23, lesson24, lesson25, lesson26));

        sectionRepository.save(section14);
        sectionRepository.save(section15);


        Course course6 = new Course();
        course6.setTitle("Trilha Soft Skills");
        course6.setDescription("Trilha direcionada a quem quer dar seus primeiros passos nessa jornada, de forma bem completa.Boa Viagem!");
        course6.setCreator("Orange Juice");
        course6.setCategory(StackCategories.SOFT_SKILLS);
        course6.setDifficulty(Difficulty.BEGINNER);
        course6.setVisible(true);
        course6.setSections(List.of(section14, section15));

        courseRepository.save(course6);

    }

    @Bean
    public InternalResourceViewResolver defaultViewResolver() {
        return new InternalResourceViewResolver();
    }
}
