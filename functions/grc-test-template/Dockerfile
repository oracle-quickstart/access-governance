FROM fnproject/fn-java-fdk:jre17-1.0.180
WORKDIR /function
COPY ./target/*.jar /function/app/
CMD ["com.oracle.idm.agcs.grc.fn.testTemplate.TestTemplateFunction::handleRequest"]
