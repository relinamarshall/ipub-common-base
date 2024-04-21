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
    protected void statementExecuteAfter(StatementProxy sp, String sql, boolean firstResult) {
        logSqlInfo(sp, sql);
    }

    @Override
    protected void statementExecuteBatchAfter(StatementProxy sp, int[] result) {
        logSqlInfo(sp, sp instanceof PreparedStatementProxy inst ? (inst.getSql()) : sp.toString());
    }

    @Override
    protected void statementExecuteQueryAfter(StatementProxy sp, String sql, ResultSetProxy resultSet) {
        logSqlInfo(sp, sql);
    }

    @Override
    protected void statementExecuteUpdateAfter(StatementProxy sp, String sql, int updateCount) {
        logSqlInfo(sp, sql);
    }

    @Override
    protected void statement_executeErrorAfter(StatementProxy sp, String sql, Throwable error) {
        log.error("exec sql failed! {}", fmtSql(sp, sql), error);
    }

    /**
     * getFormatSql
     *
     * @param sp  StatementProxy
     * @param sql String
     * @return String
     */
    private String fmtSql(StatementProxy sp, String sql) {
        String formatSql = sql;
        int size = sp.getParametersSize();
        if (size > 0) {
            List<Object> parameters = new ArrayList<>(size);
            IntStream.range(0, size).forEach(i -> {
                JdbcParameter parameter = sp.getParameter(i);
                parameters.add(parameter != null ? parameter.getValue() : null);
            });

            String dbType = sp.getConnectionProxy().getDirectDataSource().getDbType();
            formatSql = SQLUtils.format(sql, DbType.valueOf(dbType), parameters, formatOption);
        }
        return patternBlank.matcher(formatSql).replaceAll(" ");
    }

    /**
     * getExecTimeMs
     *
     * @param sp StatementProxy
     * @return long
     */
    private long execTs(StatementProxy sp) {
        sp.setLastExecuteTimeNano();
        return (long) (sp.getLastExecuteTimeNano() / 1000000D);
    }


    /**
     * logSqlInfo
     *
     * @param sp  StatementProxy
     * @param sql String
     */
    private void logSqlInfo(StatementProxy sp, String sql) {
        log.info("exec sql cost {}ms, {}.", execTs(sp), fmtSql(sp, sql));
    }
}
