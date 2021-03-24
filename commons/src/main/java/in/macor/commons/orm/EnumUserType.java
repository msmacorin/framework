package in.macor.commons.orm;

import in.macor.commons.iface.IEnums;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 * EnumType que pode ser tipado com Integer, Long e String.
 * Utilizado para criação de userTypes de enumeration para o hibernate.
 * @param <T>
 */
public abstract class EnumUserType<T> implements UserType {

    private final Class<T> typeClass = (Class<T>) ((ParameterizedType) getClass()
            .getGenericSuperclass()).getActualTypeArguments()[0];

    @Override
    public Object nullSafeGet(ResultSet rs, String[] names, SessionImplementor session, Object owner)
            throws HibernateException, SQLException {
        T codigo = (T) rs.getObject(names[0]);
        if (rs.wasNull()) {
            return null;
        }
        return getValue(codigo);
    }

    protected abstract Object getValue(T codigo);

    @Override
    public void nullSafeSet(PreparedStatement st, Object value, int index, SessionImplementor session)
            throws HibernateException, SQLException {

        if (Integer.class.isAssignableFrom(typeClass)) {
            if (value == null) {
                st.setNull(index, Types.INTEGER);
            } else {
                st.setInt(index, ((IEnums<Integer>) value).getCodigo());
            }
        } else if (String.class.isAssignableFrom(typeClass)) {
            if (value == null) {
                st.setNull(index, Types.VARCHAR);
            } else {
                st.setString(index, (String) ((IEnums<String>) value).getCodigo());
            }
        } else if (Long.class.isAssignableFrom(typeClass)) {
            if (value == null) {
                st.setNull(index, Types.BIGINT);
            } else {
                st.setLong(index, ((IEnums<Long>) value).getCodigo());
            }
        }
    }

    @Override
    public Object assemble(Serializable cached, Object owner)
            throws HibernateException {
        return cached;
    }

    @Override
    public Object deepCopy(Object value) throws HibernateException {
        return value;
    }

    @Override
    public Serializable disassemble(Object value) throws HibernateException {
        return (Serializable) value;
    }

    @Override
    public boolean equals(Object x, Object y) throws HibernateException {
        return x == y;
    }

    @Override
    public int hashCode(Object x) throws HibernateException {
        return x == null ? 0 : x.hashCode();
    }

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public Object replace(Object original, Object target, Object owner)
            throws HibernateException {
        return original;
    }

    @Override
    public int[] sqlTypes() {
        return new int[]{Types.INTEGER};
    }
}
