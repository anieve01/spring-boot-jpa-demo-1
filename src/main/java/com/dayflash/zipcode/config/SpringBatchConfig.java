package com.dayflash.zipcode.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import com.dayflash.zipcode.model.Zipcode;


@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {
	
	@Value("${input.file.zipcodes}")
	private String inputFileWithZipcodes;
	
	private static final String [] CSV_COLUMN_NAMES = new String[]{"Zip Code", "Total Population", "Median Age", "Total Males", "Total Females", "Total Households", "Average Household Size"};
	private static final String DELIMITER = ",";

    @Bean
    public Job job(JobBuilderFactory jobBuilderFactory,
                   StepBuilderFactory stepBuilderFactory,
                   ItemReader<Zipcode> itemReader,
                   ItemProcessor<Zipcode, Zipcode> itemProcessor,
                   ItemWriter<Zipcode> itemWriter
    ) {

        Step step = stepBuilderFactory.get("ETL-file-load")
                .<Zipcode, Zipcode>chunk(100)
                .reader(itemReader)
                .processor(itemProcessor)
                .writer(itemWriter)
                .build();


        return jobBuilderFactory.get("ETL-Load")
                .incrementer(new RunIdIncrementer())
                .start(step)
                .build();
    }

    @Bean
    public FlatFileItemReader<Zipcode> itemReader() {

        FlatFileItemReader<Zipcode> flatFileItemReader = new FlatFileItemReader<>();
        flatFileItemReader.setResource(new FileSystemResource(inputFileWithZipcodes));
        flatFileItemReader.setName("CSV-Reader");
        flatFileItemReader.setLinesToSkip(1);
        flatFileItemReader.setLineMapper(lineMapper());
        return flatFileItemReader;
    }

    @Bean
    public LineMapper<Zipcode> lineMapper() {

        DefaultLineMapper<Zipcode> defaultLineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();

        lineTokenizer.setDelimiter(DELIMITER);
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames(CSV_COLUMN_NAMES);

        BeanWrapperFieldSetMapper<Zipcode> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Zipcode.class);

        defaultLineMapper.setLineTokenizer(lineTokenizer);
        defaultLineMapper.setFieldSetMapper(fieldSetMapper);

        return defaultLineMapper;
    }

}
