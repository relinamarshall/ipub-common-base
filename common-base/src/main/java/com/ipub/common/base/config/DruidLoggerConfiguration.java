package com.ipub.common.base.config;

import com.alibaba.druid.DbType;
import com.alibaba.druid.filter.logging.Slf4jLogFilter;
import com.alibaba.druid.proxy.jdbc.JdbcParameter;
import com.alibaba.druid.proxy.jdbc.PreparedStatementProxy;
import com.alibaba.druid.proxy.jdbc.ResultSetProxy;
import com.alibaba.druid.proxy.jdbc.StatementProxy;
import com.alibaba.druid.sql.SQLUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

/**
 * DruidSlf4jLoggerConfiguration
 *
 * @author wen.zhou
 * @since 2024/4/20
 */
@Slf4j
@AutoConfiguration
public class DruidLoggerConfiguration extends Slf4jLogFilter {
    /**
     * patternBlank
     */
    private final Pattern patternBlank = Pattern.compile("\\s+");
    /**
     * formatOption
     */
    private final SQLUtils.FormatOption formatOption = new SQLUtils.FormatOption(false, true);

    @Override
    protected void statementExecuteAfter(StatementProxy statement, String sql, boolean firstResult) {
        log.info("exec sql cost {}ms, {}.", execTs(statement), fmtSql(statement, sql));
    }

    @Override
    protected void statementExecuteBatchAfter(StatementProxy statement, int[] result) {
        String sql = statement instanceof PreparedStatementProxy ? ((PreparedStatementProxy) statement).getSql() :
                statement.toString();
        log.info("exec batch sql cost {}ms, {}.", execTs(statement), fmtSql(statement, sql));
    }

    @Override
    protected void statementExecuteQueryAfter(StatementProxy statement, String sql, ResultSetProxy resultSet) {
        log.info("exec query sql cost {}ms, {}.", execTs(statement), fmtSql(statement, sql));
    }

    @Override
    protected void statementExecuteUpdateAfter(StatementProxy statement, String sql, int updateCount) {
        log.info("exec update sql cost {}ms, {}.", execTs(statement), fmtSql(statement, sql));
    }

    @Override
    protected void statement_executeErrorAfter(StatementProxy statement, String sql, Throwable error) {
        log.error("exec sql failed! {}", fmtSql(statement, sql), error);
    }

    /**
     * getFormatSql
     *
     * @param statement StatementProxy
     * @param sql       String
     * @return String
     */
    private String fmtSql(StatementProxy statement, String sql) {
        String formatSql = sql;
        int parametersSize = statement.getParametersSize();
        if (parametersSize > 0) {
            List<Object> parameters = new ArrayList<>(parametersSize);
            IntStream.range(0, parametersSize).forEach(i -> {
                JdbcParameter parameter = statement.getParameter(i);
                parameters.add(parameter != null ? parameter.getValue() : null);
            });

            String dbType = statement.getConnectionProxy().getDirectDataSource().getDbType();
            formatSql = SQLUtils.format(sql, DbType.valueOf(dbType), parameters, formatOption);
        }
        return patternBlank.matcher(formatSql).replaceAll(" ");
    }

    /**
     * getExecTimeMs
     *
     * @param statement StatementProxy
     * @return long
     */
    private long execTs(StatementProxy statement) {
        statement.setLastExecuteTimeNano();
        return (long) (statement.getLastExecuteTimeNano() / 1000000D);
    }
}
