import { Card } from "@/components/ui/card";
import { Input } from "@/components/ui/input";

export default function CustomerForm({
  customer,
  onChange
}) {
  return (
    <Card className="p-6 rounded-xl space-y-6">
      <h2 className="text-lg font-semibold">
        Customer Details
      </h2>

      <div className="grid md:grid-cols-2 gap-4">
        <Input
          name="firstName"
          placeholder="First Name"
          value={customer.firstName}
          onChange={onChange}
        />

        <Input
          name="lastName"
          placeholder="Last Name"
          value={customer.lastName}
          onChange={onChange}
        />

        <Input
          name="email"
          placeholder="Email Address"
          value={customer.email}
          onChange={onChange}
          className="md:col-span-2"
        />

        <Input
          name="addressLine1"
          placeholder="Address Line 1"
          value={customer.addressLine1}
          onChange={onChange}
          className="md:col-span-2"
        />

        <Input
          name="addressLine2"
          placeholder="Address Line 2"
          value={customer.addressLine2}
          onChange={onChange}
          className="md:col-span-2"
        />

        <Input
          name="city"
          placeholder="City"
          value={customer.city}
          onChange={onChange}
        />

        <Input
          name="provinceState"
          placeholder="Province / State"
          value={customer.provinceState}
          onChange={onChange}
        />

        <Input
          name="postalCode"
          placeholder="Postal Code"
          value={customer.postalCode}
          onChange={onChange}
        />

        <Input
          name="country"
          placeholder="Country"
          value={customer.country}
          onChange={onChange}
        />
      </div>
    </Card>
  );
}